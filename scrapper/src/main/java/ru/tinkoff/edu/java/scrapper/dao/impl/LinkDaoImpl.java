package ru.tinkoff.edu.java.scrapper.dao.impl;

import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import lombok.RequiredArgsConstructor;
import org.springframework.jdbc.core.DataClassRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;
import ru.tinkoff.edu.java.scrapper.dao.LinkDao;
import ru.tinkoff.edu.java.scrapper.dto.entity.Link;

import java.util.List;

@SuppressWarnings("SqlNoDataSourceInspection")
@Repository
@RequiredArgsConstructor
public final class LinkDaoImpl implements LinkDao {

    private static final @NotNull String SELECT_LINK_BY_ID_QUERY = """
            SELECT *
              FROM link
             WHERE link_id = ?
            """;

    private static final @NotNull String SELECT_ALL_LINKS_QUERY = """
            SELECT *
              FROM link
            """;

    private static final @NotNull String INSERT_NEW_LINK_QUERY = """
            INSERT INTO link(url)
            VALUES (?)
            """;

    private static final @NotNull String DELETE_LINK_QUERY = """
            DELETE
              FROM link
             WHERE link_id = ?
            """;

    private static final @NotNull RowMapper<Link> LINK_ROW_MAPPER = new DataClassRowMapper<>(Link.class);

    private final @NotNull JdbcTemplate jdbcTemplate;

    @Override
    public Link get(long id) {
        return jdbcTemplate.query(SELECT_LINK_BY_ID_QUERY, LINK_ROW_MAPPER, id)
                .stream()
                .findAny()
                .orElse(null);
    }

    @Override
    public @NotNull List<@NotNull Link> getAll() {
        return jdbcTemplate.query(SELECT_ALL_LINKS_QUERY, LINK_ROW_MAPPER);
    }

    @Override
    public void save(@NotNull Link element) {
        jdbcTemplate.update(INSERT_NEW_LINK_QUERY, element.url().toString());
    }

    @Override
    public void delete(@Positive long id) {
        jdbcTemplate.update(DELETE_LINK_QUERY, id);
    }
}
