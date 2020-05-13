package ru.otus.library.dao;

import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcOperations;
import org.springframework.stereotype.Repository;
import ru.otus.library.domain.Author;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Repository
public class AuthorDaoJdbc implements AuthorDao {

    private final NamedParameterJdbcOperations jdbc;

    public AuthorDaoJdbc(NamedParameterJdbcOperations jdbc) {
        this.jdbc = jdbc;
    }

    @Override
    public int count() {
        return jdbc.getJdbcOperations().queryForObject("select count(*) from authors", null, Integer.class);
    }

    @Override
    public void insert(Author author) {
        jdbc.getJdbcOperations().
                update("insert into authors (id, `name`, `surname`) values (?, ?, ?)",
                        author.getId(), author.getName(), author.getSurname());
    }

    @Override
    public void update(Author author, String nameNew, String surnameNew) {
        jdbc.getJdbcOperations().
                update("update authors set `name` = ? , `surname` = ? where `name` = ? and `surname` = ? ",
                        nameNew, surnameNew, author.getName(), author.getSurname());
    }

    @Override
    public Author getBySurnameAndName(String name, String surname) {
        Map<String, Object> params = new HashMap<>();
        params.put("name", name);
        params.put("surname", surname);
        return jdbc.queryForObject("select * from authors where name = :name and surname = :surname",
                params, new AuthorMapper());
    }

    @Override
    public List<Author> getAll() {
        return jdbc.query("select * from authors", new AuthorMapper());
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
