package ru.tinkoff.edu.java.scrapper.dao.impl.jdbc;

import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.DataClassRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;
import ru.tinkoff.edu.java.scrapper.dao.ChatDao;
import ru.tinkoff.edu.java.scrapper.dto.entity.Chat;

import java.net.URI;
import java.util.List;

@SuppressWarnings({ "SqlNoDataSourceInspection", "SqlResolve", "SqlCheckUsingColumns" })
@Repository
public final class JdbcChatDao implements ChatDao {

    private static final @NotNull RowMapper<Chat> CHAT_ROW_MAPPER = new DataClassRowMapper<>(Chat.class);

//    --- SELECT queries ---
    private static final @NotNull String SELECT_CHAT_BY_ID_QUERY = """
            SELECT *
              FROM chat
             WHERE chat_id = ?
            """;

    private static final @NotNull String SELECT_ALL_CHATS_QUERY = """
            SELECT *
              FROM chat
            """;

    private static final @NotNull String SELECT_CHATS_BY_LINK_ID_QUERY = """
            SELECT c.chat_id
              FROM chat           AS c
                   JOIN chat_link AS c_l USING(chat_id)
             WHERE c_l.link_id = ?
            """;

    private static final @NotNull String SELECT_CHATS_BY_LINK_URL_QUERY = """
            SELECT c.chat_id
              FROM chat           AS  c
                   JOIN chat_link        USING(chat_id)
                   JOIN link      AS  l  USING(link_id)
             WHERE l.url = ?
            """;

//    --- INSERT queries ---
    private static final @NotNull String INSERT_NEW_CHAT_QUERY = """
            INSERT INTO chat(chat_id)
            VALUES (?)
            """;

//    --- DELETE queries ---
    private static final @NotNull String DELETE_CHAT_QUERY = """
            DELETE
              FROM chat
             WHERE chat_id = ?
            """;

    private final @NotNull JdbcTemplate jdbcTemplate;

    @Autowired
    public JdbcChatDao(@NotNull JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    @Override
    public Chat get(long chatId) {
        return jdbcTemplate.query(SELECT_CHAT_BY_ID_QUERY, CHAT_ROW_MAPPER, chatId)
                .stream()
                .findAny()
                .orElse(null);
    }

    @Override
    public @NotNull List<@NotNull Chat> getAll() {
        return jdbcTemplate.query(SELECT_ALL_CHATS_QUERY, CHAT_ROW_MAPPER);
    }

    @Override
    public @NotNull List<@NotNull Chat> getByLinkId(@Positive long linkId) {
        return jdbcTemplate.query(SELECT_CHATS_BY_LINK_ID_QUERY, CHAT_ROW_MAPPER, linkId);
    }

    @Override
    public @NotNull List<@NotNull Chat> getByLinkUrl(@NotNull URI url) {
        return jdbcTemplate.query(SELECT_CHATS_BY_LINK_URL_QUERY, CHAT_ROW_MAPPER, url.toString());
    }

    @Override
    public void save(@NotNull Chat element) {
        jdbcTemplate.update(INSERT_NEW_CHAT_QUERY, element.getChatId());
    }

    @Override
    public void delete(@Positive long chatId) {
        jdbcTemplate.update(DELETE_CHAT_QUERY, chatId);
    }
}
