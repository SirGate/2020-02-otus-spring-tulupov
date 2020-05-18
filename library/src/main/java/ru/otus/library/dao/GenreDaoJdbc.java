package ru.otus.library.dao;

import org.springframework.dao.DataAccessException;
import org.springframework.jdbc.core.PreparedStatementCallback;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcOperations;
import org.springframework.stereotype.Service;
import ru.otus.library.domain.Genre;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class GenreDaoJdbc implements GenreDao {

    private final NamedParameterJdbcOperations jdbc;

    public GenreDaoJdbc(NamedParameterJdbcOperations jdbc) {
        this.jdbc = jdbc;
    }

    @Override
    public int count() {
        return jdbc.getJdbcOperations().queryForObject("select count(id) from genres", null, Integer.class);
    }

    @Override
    public void insert(Genre genre) {
        String query = "insert into genres values (:id, :description)";
        Map<String, Object> params = new HashMap<>();
        params.put("id", genre.getId());
        params.put("description", genre.getDescription());
        jdbc.execute(query, params, new PreparedStatementCallback() {
            @Override
            public Object doInPreparedStatement(PreparedStatement ps)
                    throws SQLException, DataAccessException {
                return ps.executeUpdate();
            }
        });
    }

    @Override
    public void update(Genre genre, String descriptionNew) {
        String query = "update genres set description = :descriptionNew where description = :description";
        Map<String, Object> params = new HashMap<>();
        params.put("descriptionNew", descriptionNew);
        params.put("description", genre.getDescription());
        jdbc.execute(query, params, new PreparedStatementCallback() {
            @Override
            public Object doInPreparedStatement(PreparedStatement ps)
                    throws SQLException, DataAccessException {
                return ps.executeUpdate();
            }
        });
    }

    @Override
    public Genre getByDescription(String description) {
        Map<String, Object> params = Collections.singletonMap("description", description);
        return jdbc.queryForObject(
                "select id, description from genres where description = :description", params, new GenreMapper()
        );
    }

    @Override
    public List<Genre> getAll() {
        return jdbc.query("select id, description from genres", new GenreDaoJdbc.GenreMapper());
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
