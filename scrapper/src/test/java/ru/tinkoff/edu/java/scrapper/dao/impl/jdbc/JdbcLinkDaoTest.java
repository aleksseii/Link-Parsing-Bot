package ru.tinkoff.edu.java.scrapper.dao.impl.jdbc;

import jakarta.validation.constraints.NotNull;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.test.annotation.Rollback;
import org.springframework.transaction.annotation.Transactional;
import org.testcontainers.shaded.org.hamcrest.MatcherAssert;
import org.testcontainers.shaded.org.hamcrest.Matchers;
import ru.tinkoff.edu.java.scrapper.IntegrationEnvironment;
import ru.tinkoff.edu.java.scrapper.dao.LinkDao;
import ru.tinkoff.edu.java.scrapper.dto.entity.Link;

import java.net.URI;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.testcontainers.shaded.org.hamcrest.MatcherAssert.assertThat;
import static org.testcontainers.shaded.org.hamcrest.Matchers.containsInAnyOrder;

@SuppressWarnings({ "SqlNoDataSourceInspection", "SqlResolve" })
@SpringBootTest
class JdbcLinkDaoTest extends IntegrationEnvironment {

    private static final long EXISTING_LINK_ID_1 = 1L;
    private static final long EXISTING_LINK_ID_2 = 2L;
    private static final long EXISTING_LINK_ID_3 = 3L;

    private static final @NotNull URI EXISTING_LINK_URL_1 = URI.create("https://existing-url.com");
    private static final @NotNull URI EXISTING_LINK_URL_2 = URI.create("https://another-url.com");
    private static final @NotNull URI EXISTING_LINK_URL_3 = URI.create("https://another-one-url.com");

    private static final @NotNull Link EXISTING_LINK_1 = new Link(EXISTING_LINK_ID_1, EXISTING_LINK_URL_1);
    private static final @NotNull Link EXISTING_LINK_2 = new Link(EXISTING_LINK_ID_2, EXISTING_LINK_URL_2);
    private static final @NotNull Link EXISTING_LINK_3 = new Link(EXISTING_LINK_ID_3, EXISTING_LINK_URL_3);
    private static final @NotNull List<@NotNull Link> ALL_EXISTING_LINKS = List.of(
            EXISTING_LINK_1,
            EXISTING_LINK_2,
            EXISTING_LINK_3
    );

    private static final long EXISTING_CHAT_ID_1 = 123L;
    private static final long EXISTING_CHAT_ID_2 = 246L;
    private static final long EXISTING_CHAT_ID_3 = 492L;

    public static final @NotNull String INSERT_1_LINK_QUERY = """
            INSERT INTO link(url)
            VALUES (?)
            """;

    public static final @NotNull String INSERT_3_LINKS_QUERY = """
            INSERT INTO link(url)
            VALUES (?),
                   (?),
                   (?)
            """;

    public static final @NotNull String INSERT_3_CHATS_QUERY = """
            INSERT INTO chat(chat_id)
            VALUES (?),
                   (?),
                   (?)
            """;

//    public static final @NotNull String INSERT_3_CHATS_QUERY = """
//            INSERT INTO chat_link(chat_id, link_id)
//            VALUES (?),
//                   (?),
//                   (?)
//            """;

    @Autowired
    private @NotNull JdbcTemplate jdbcTemplate;

    @Autowired
    private @NotNull LinkDao linkDao;

    @Test
    @Transactional
    @Rollback
    void get_shouldReturnLink_whenProvidedExistingLinkId() {
        jdbcTemplate.update(INSERT_1_LINK_QUERY, EXISTING_LINK_URL_1);

        Link actualLink = linkDao.get(EXISTING_LINK_ID_1);
        assertEquals(EXISTING_LINK_1, actualLink);
    }

    @Test
    @Transactional
    @Rollback
    void getByUrl_shouldReturnLink_whenProvidedExistingLinkUrl() {
        jdbcTemplate.update(INSERT_1_LINK_QUERY, EXISTING_LINK_URL_1);

        Link actualLink = linkDao.getByUrl(EXISTING_LINK_URL_1);
        assertEquals(EXISTING_LINK_1, actualLink);
    }

    @Test
    @Transactional
    @Rollback
    void getAll_shouldReturnAllLinks() {
        jdbcTemplate.update(
                INSERT_3_LINKS_QUERY,
                EXISTING_LINK_URL_1.toString(),
                EXISTING_LINK_URL_2.toString(),
                EXISTING_LINK_URL_3.toString()
        );

        List<Link> allActualLinks = linkDao.getAll();
        assertNotNull(allActualLinks);
        assertEquals(ALL_EXISTING_LINKS.size(), allActualLinks.size());
        assertThat(ALL_EXISTING_LINKS, containsInAnyOrder(allActualLinks.toArray(new Link[0])));
    }

    @Test
    @Transactional
    @Rollback
    void getByChatId() {
        insertSubscriptions();


    }

    @Test
    void save() {
    }

    @Test
    void delete() {
    }

    @Test
    void testDelete() {
    }

    private void insertSubscriptions() {
        jdbcTemplate.update(
                INSERT_3_CHATS_QUERY,
                EXISTING_CHAT_ID_1,
                EXISTING_CHAT_ID_2,
                EXISTING_CHAT_ID_3
        );
        jdbcTemplate.update(
                INSERT_3_LINKS_QUERY,
                EXISTING_LINK_URL_1.toString(),
                EXISTING_LINK_URL_2.toString(),
                EXISTING_LINK_URL_3.toString()
        );
        jdbcTemplate.update(

        );
    }
}
