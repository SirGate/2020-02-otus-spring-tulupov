package ru.otus.library.dao;

import org.springframework.dao.DataAccessException;
import org.springframework.jdbc.core.PreparedStatementCallback;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcOperations;
import org.springframework.stereotype.Service;
import ru.otus.library.domain.Author;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class AuthorDaoJdbc implements AuthorDao {

    private final NamedParameterJdbcOperations jdbc;

    public AuthorDaoJdbc(NamedParameterJdbcOperations jdbc) {
        this.jdbc = jdbc;
    }

    @Override
    public int count() {
        return jdbc.getJdbcOperations().queryForObject("select count(id) from authors", null, Integer.class);
    }

    public void insert(Author author) {
        String query = "insert into authors values (:id, :name, :surname)";
        Map<String, Object> params = new HashMap<>();
        params.put("id", author.getId());
        params.put("name", author.getName());
        params.put("surname", author.getSurname());
        jdbc.execute(query, params, new PreparedStatementCallback() {
            @Override
            public Object doInPreparedStatement(PreparedStatement ps)
                    throws SQLException, DataAccessException {
                return ps.executeUpdate();
            }
        });
    }

    @Override
    public void update(Author author, String nameNew, String surnameNew) {
        String query = "update authors set name = :nameNew, surname = :surnameNew " +
                "where name = :name and surname = :surname ";

        Map<String, Object> params = new HashMap<>();
        params.put("nameNew", nameNew);
        params.put("surnameNew", surnameNew);
        params.put("name", author.getName());
        params.put("surname", author.getSurname());
        jdbc.execute(query, params, new PreparedStatementCallback() {
            @Override
            public Object doInPreparedStatement(PreparedStatement ps)
                    throws SQLException, DataAccessException {
                return ps.executeUpdate();
            }
        });
    }

    @Override
    public Author getBySurnameAndName(String name, String surname) {
        Map<String, Object> params = new HashMap<>();
        params.put("name", name);
        params.put("surname", surname);
        return jdbc.queryForObject("select id, name, surname from authors where name = :name and surname = :surname",
                params, new AuthorMapper());
    }

    @Override
    public List<Author> getAll() {
        return jdbc.query("select id, name, surname from authors", new AuthorMapper());
    }

    @Override
    public void deleteBySurnameAndName(String name, String surname) {
        Map<String, Object> params = new HashMap<>();
        params.put("name", name);
        params.put("surname", surname);
        jdbc.update(
                "delete from authors where name = :name and surname = :surname", params);

    }

    private static class AuthorMapper implements RowMapper<Author> {

        @Override
        public Author mapRow(ResultSet resultSet, int i) throws SQLException {
            long id = resultSet.getLong("id");
            String name = resultSet.getString("name");
            String surname = resultSet.getString("surname");
            return new Author(id, name, surname);
        }
    }
}
