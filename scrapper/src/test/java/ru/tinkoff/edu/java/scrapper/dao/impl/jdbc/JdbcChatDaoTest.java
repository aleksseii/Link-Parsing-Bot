package ru.tinkoff.edu.java.scrapper.dao.impl.jdbc;

import jakarta.validation.constraints.NotNull;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.test.annotation.Rollback;
import org.springframework.transaction.annotation.Transactional;
import ru.tinkoff.edu.java.scrapper.IntegrationEnvironment;
import ru.tinkoff.edu.java.scrapper.dao.ChatDao;
import ru.tinkoff.edu.java.scrapper.dto.entity.Chat;

import java.net.URI;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.testcontainers.shaded.org.hamcrest.MatcherAssert.assertThat;
import static org.testcontainers.shaded.org.hamcrest.Matchers.containsInAnyOrder;

@SuppressWarnings({ "SqlNoDataSourceInspection", "SqlResolve" })
@SpringBootTest
class JdbcChatDaoTest extends IntegrationEnvironment {

    private static final long EXISTING_CHAT_ID_1 = 123L;
    private static final long EXISTING_CHAT_ID_2 = 246L;
    private static final long EXISTING_CHAT_ID_3 = 492L;
    private static final @NotNull Chat EXISTING_CHAT_1 = new Chat(EXISTING_CHAT_ID_1);
    private static final @NotNull Chat EXISTING_CHAT_2 = new Chat(EXISTING_CHAT_ID_2);
    private static final @NotNull List<Chat> ALL_EXISTING_CHATS = List.of(
            EXISTING_CHAT_1,
            EXISTING_CHAT_2,
            new Chat(EXISTING_CHAT_ID_3)
    );

    private static final long EXISTING_LINK_ID = 1L;
    private static final long NON_EXISTING_LINK_ID = 2L;
    private static final @NotNull URI EXISTING_LINK_URL = URI.create("https://expected-url.com");

    private static final String INSERT_3_CHATS_QUERY = """
            INSERT INTO chat(chat_id)
            VALUES (?),
                   (?),
                   (?)
            """;

    private static final @NotNull String INSERT_LINK_QUERY = """
            INSERT INTO link(url)
            VALUES (?)
            """;

    private static final @NotNull String INSERT_3_SUBSCRIPTIONS_QUERY = """
            INSERT INTO chat_link(chat_id, link_id)
            VALUES (?, ?),
                   (?, ?),
                   (?, ?)
            """;

    @Autowired
    private @NotNull JdbcTemplate jdbcTemplate;

    @Autowired
    private @NotNull ChatDao chatDao;

    @Test
    @Transactional
    @Rollback
    void get_shouldReturnChat_whenProvidedExistingChatId() {
        jdbcTemplate.update("""
                        INSERT INTO chat(chat_id)
                        VALUES (?)
                        """,
                EXISTING_CHAT_ID_1
        );

        Chat actualChat = chatDao.get(EXISTING_CHAT_ID_1);
        assertEquals(EXISTING_CHAT_1, actualChat);
    }

    @Test
    @Transactional
    @Rollback
    void getAll_shouldReturnAllChats() {
        jdbcTemplate.update(
                INSERT_3_CHATS_QUERY,
                EXISTING_CHAT_ID_1,
                EXISTING_CHAT_ID_2,
                EXISTING_CHAT_ID_3
        );

        List<Chat> allActualChats = chatDao.getAll();
        assertNotNull(allActualChats);
        assertEquals(ALL_EXISTING_CHATS.size(), allActualChats.size());
        assertThat(ALL_EXISTING_CHATS, containsInAnyOrder(allActualChats.toArray(new Chat[0])));
    }

    @Test
    @Transactional
    @Rollback
    void getByLinkId_shouldReturnAllChatsTrackingTheLink_whenProvidedLinkId() {
        insertChatsAndLinksAndSubscriptions();

        List<Chat> actualChats = chatDao.getByLinkId(EXISTING_LINK_ID);
        assertNotNull(actualChats);
        assertEquals(actualChats.size(), 2);
        assertThat(actualChats, containsInAnyOrder(
                EXISTING_CHAT_1,
                EXISTING_CHAT_2
        ));
    }

    @Test
    @Transactional
    @Rollback
    void getByLinkUrl_shouldReturnAllChatsTrackingTheLink_whenProvidedLinkUrl() {
        insertChatsAndLinksAndSubscriptions();

        List<Chat> actualChats = chatDao.getByLinkUrl(EXISTING_LINK_URL);
        assertNotNull(actualChats);
        assertEquals(actualChats.size(), 2);
        assertThat(actualChats, containsInAnyOrder(
                EXISTING_CHAT_1,
                EXISTING_CHAT_2
        ));
    }

    @Test
    @Transactional
    @Rollback
    void save_shouldSaveNewChat() {
        int sizeBefore = chatDao.getAll().size();
        chatDao.save(EXISTING_CHAT_1);

        int sizeAfter = chatDao.getAll().size();
        Chat actual = chatDao.get(EXISTING_CHAT_ID_1);

        assertEquals(sizeBefore + 1, sizeAfter);
        assertEquals(EXISTING_CHAT_1, actual);
    }

    @Test
    @Transactional
    @Rollback
    void delete_shouldDeleteChat_whenProvidedChatId() {
        chatDao.save(EXISTING_CHAT_1);

        int sizeBefore = chatDao.getAll().size();
        chatDao.delete(EXISTING_CHAT_ID_1);

        int sizeAfter = chatDao.getAll().size();
        Chat chat = chatDao.get(EXISTING_CHAT_ID_1);

        assertEquals(sizeBefore - 1, sizeAfter);
        assertNull(chat);
    }

    private void insertChatsAndLinksAndSubscriptions() {
        jdbcTemplate.update(
                INSERT_3_CHATS_QUERY,
                EXISTING_CHAT_ID_1,
                EXISTING_CHAT_ID_2,
                EXISTING_CHAT_ID_3
        );
        jdbcTemplate.update(INSERT_LINK_QUERY, EXISTING_LINK_URL.toString());
        jdbcTemplate.update(
                INSERT_3_SUBSCRIPTIONS_QUERY,
                EXISTING_CHAT_ID_1, EXISTING_LINK_ID,
                EXISTING_CHAT_ID_2, EXISTING_LINK_ID,
                EXISTING_CHAT_ID_3, NON_EXISTING_LINK_ID
        );
    }
}
