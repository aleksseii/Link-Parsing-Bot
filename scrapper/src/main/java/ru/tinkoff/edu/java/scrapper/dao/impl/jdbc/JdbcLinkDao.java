package ru.tinkoff.edu.java.scrapper.dao.impl.jdbc;

import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import lombok.RequiredArgsConstructor;
import org.springframework.jdbc.core.DataClassRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;
import ru.tinkoff.edu.java.scrapper.dao.LinkDao;
import ru.tinkoff.edu.java.scrapper.dto.entity.Link;

import java.net.URI;
import java.util.List;

@SuppressWarnings({ "SqlNoDataSourceInspection", "SqlResolve", "SqlCheckUsingColumns" })
@Repository
@RequiredArgsConstructor
public final class JdbcLinkDao implements LinkDao {

    private static final @NotNull RowMapper<Link> LINK_ROW_MAPPER = new DataClassRowMapper<>(Link.class);

//    --- SELECT queries ---
    private static final @NotNull String SELECT_LINK_BY_ID_QUERY = """
            SELECT *
              FROM link
             WHERE link_id = ?
            """;

    private static final @NotNull String SELECT_ALL_LINKS_QUERY = """
            SELECT *
              FROM link
            """;

    private static final @NotNull String SELECT_LINKS_BY_CHAT_ID = """
            SELECT l.link_id, l.url
              FROM link           AS l
                   JOIN chat_link AS c_l USING(link_id)
             WHERE c_l.chat_id = ?
            """;

//    --- INSERT queries ---
    private static final @NotNull String INSERT_NEW_LINK_QUERY = """
            INSERT INTO link(url)
            VALUES (?)
            """;

//    --- DELETE queries ---
    private static final @NotNull String DELETE_LINK_BY_ID_QUERY = """
            DELETE
              FROM link
             WHERE link_id = ?
            """;

    private static final @NotNull String DELETE_LINK_BY_URL_QUERY = """
            DELETE
              FROM link
             WHERE url = ?
            """;

    private final @NotNull JdbcTemplate jdbcTemplate;

    @Override
    public Link get(long linkId) {
        return jdbcTemplate.query(SELECT_LINK_BY_ID_QUERY, LINK_ROW_MAPPER, linkId)
                .stream()
                .findAny()
                .orElse(null);
    }

    @Override
    public @NotNull List<@NotNull Link> getAll() {
        return jdbcTemplate.query(SELECT_ALL_LINKS_QUERY, LINK_ROW_MAPPER);
    }

    @Override
    public List<@NotNull Link> getByChatId(@Positive long chatId) {
        return jdbcTemplate.query(SELECT_LINKS_BY_CHAT_ID, LINK_ROW_MAPPER, chatId);
    }

    @Override
    public void save(@NotNull Link element) {
        jdbcTemplate.update(INSERT_NEW_LINK_QUERY, element.getUrl().toString());
    }

    @Override
    public void delete(@Positive long id) {
        jdbcTemplate.update(DELETE_LINK_BY_ID_QUERY, id);
    }

    @Override
    public void delete(@NotNull URI url) {
        jdbcTemplate.update(DELETE_LINK_BY_URL_QUERY, url.toString());
    }
}
