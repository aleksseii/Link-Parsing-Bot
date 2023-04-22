package ru.tinkoff.edu.java.scrapper.dao.impl;

import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import lombok.RequiredArgsConstructor;
import org.springframework.jdbc.core.DataClassRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;
import ru.tinkoff.edu.java.scrapper.dao.ChatDao;
import ru.tinkoff.edu.java.scrapper.dto.entity.Chat;

import java.util.List;

@SuppressWarnings({ "SqlNoDataSourceInspection", "SqlResolve" })
@Repository
@RequiredArgsConstructor
public final class ChatDaoImpl implements ChatDao {

    private static final @NotNull RowMapper<Chat> CHAT_ROW_MAPPER = new DataClassRowMapper<>(Chat.class);
    private static final @NotNull String SELECT_CHAT_BY_ID_QUERY = """
            SELECT *
              FROM chat
             WHERE chat_id = ?
            """;

    private static final @NotNull String SELECT_ALL_CHATS_QUERY = """
            SELECT *
              FROM chat
            """;

    private static final @NotNull String INSERT_NEW_CHAT_QUERY = """
            INSERT INTO chat(chat_id)
            VALUES (?)
            """;

    private static final @NotNull String DELETE_CHAT_QUERY = """
            DELETE
              FROM chat
             WHERE chat_id = ?
            """;

    private final @NotNull JdbcTemplate jdbcTemplate;

    @Override
    public Chat get(long id) {
        return jdbcTemplate.query(SELECT_CHAT_BY_ID_QUERY, CHAT_ROW_MAPPER, id)
                .stream()
                .findAny()
                .orElse(null);
    }

    @Override
    public @NotNull List<@NotNull Chat> getAll() {
        return jdbcTemplate.query(SELECT_ALL_CHATS_QUERY, CHAT_ROW_MAPPER);
    }

    @Override
    public void save(@NotNull Chat element) {
        jdbcTemplate.update(INSERT_NEW_CHAT_QUERY, element.chatId());
    }

    @Override
    public void delete(@Positive long id) {
        jdbcTemplate.update(DELETE_CHAT_QUERY, id);
    }
}
