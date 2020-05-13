package ru.otus.library.dao;

import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcOperations;
import org.springframework.stereotype.Repository;
import ru.otus.library.domain.Book;
import ru.otus.library.domain.Genre;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Collections;
import java.util.List;
import java.util.Map;

@Repository
public class GenreDaoJdbc implements GenreDao {

    private final NamedParameterJdbcOperations jdbc;

    public GenreDaoJdbc(NamedParameterJdbcOperations jdbc) {
        this.jdbc = jdbc;
    }

    @Override
    public int count() {
        return jdbc.getJdbcOperations().queryForObject("select count(*) from genres", null, Integer.class);
    }

    @Override
    public void insert(Genre genre) {
        jdbc.getJdbcOperations().
                update("insert into genres (id, `description`) values (?, ?)",
                        genre.getId(), genre.getDescription());
    }

    @Override
    public void update(Genre genre, String descriptionNew) {
        jdbc.getJdbcOperations().
                update("update genres set `description` = ? where `description` = ?",
                        descriptionNew, genre.getDescription());
    }

    @Override
    public Genre getByDescription(String description) {
        Map<String, Object> params = Collections.singletonMap("description", description);
        return jdbc.queryForObject(
                "select * from genres where description = :description", params, new GenreMapper()
        );
    }

    @Override
    public List<Genre> getAll() {
        return jdbc.query("select * from genres", new GenreDaoJdbc.GenreMapper());
    }

    @Override
    public void deleteByDescription(String description) {
        Map<String, Object> params = Collections.singletonMap("description", description);
        jdbc.update(
                "delete from genres where description = :description", params
        );
    }

    private static class GenreMapper implements RowMapper<Genre> {

        @Override
        public Genre mapRow(ResultSet resultSet, int i) throws SQLException {
            long id = resultSet.getLong("id");
            String description = resultSet.getString("description");
            return new Genre(id, description);
        }
    }
}
