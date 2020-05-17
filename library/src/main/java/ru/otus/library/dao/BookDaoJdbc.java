package ru.otus.library.dao;

import org.springframework.dao.DataAccessException;
import org.springframework.jdbc.core.PreparedStatementCallback;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcOperations;
import org.springframework.stereotype.Service;
import ru.otus.library.domain.Author;
import ru.otus.library.domain.Book;
import ru.otus.library.domain.Genre;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class BookDaoJdbc implements BookDao {
    private final NamedParameterJdbcOperations jdbc;

    public BookDaoJdbc(NamedParameterJdbcOperations jdbc) {
        this.jdbc = jdbc;
    }

    @Override
    public int count() {
        return jdbc.getJdbcOperations().queryForObject("select count(id) from books", null, Integer.class);
    }

    public void insert(Book book) {
        String query = "insert into books values (:id, :authorID, :authorName, " +
                ":authorSurname, :genreID, :genreDescription, :title)";
        Map<String, Object> params = new HashMap<>();
        params.put("id", book.getId());
        params.put("authorID", book.getAuthor().getId());
        params.put("authorName", book.getAuthor().getName());
        params.put("authorSurname", book.getAuthor().getSurname());
        params.put("genreID", book.getGenre().getId());
        params.put("genreDescription", book.getGenre().getDescription());
        params.put("title", book.getTitle());
        jdbc.execute(query, params, new PreparedStatementCallback() {
            @Override
            public Object doInPreparedStatement(PreparedStatement ps)
                    throws SQLException, DataAccessException {
                return ps.executeUpdate();
            }
        });
    }

    @Override
    public void update(Author author, String titleNew) {
        String query = "update books set title = :titleNew where authorName = :name and authorSurname = :surname";
        Map<String, Object> params = new HashMap<>();
        params.put("titleNew", titleNew);
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
    public Book getById(long id) {
        Map<String, Object> params = Collections.singletonMap("id", id);
        return jdbc.queryForObject(
                "select id, title, authorID, authorName, authorSurname, genreID, genreDescription " +
                        "from books where id = :id", params, new BookMapper()
        );
    }

    @Override
    public List<Book> getAll() {
        return jdbc.query("select id, title, authorID, authorName, authorSurname, genreID, genreDescription " +
                "from books", new BookMapper());
    }


    @Override
    public void deleteByTitleAndAuthor(String title, Author author) {
        Map<String, Object> params = new HashMap<>();
        params.put("title", title);
        params.put("authorName", author.getName());
        params.put("authorSurname", author.getSurname());
        jdbc.update(
                "delete from books where title = :title and authorName = :authorName and authorSurname = :authorSurname",
                params
        );
    }

    private static class BookMapper implements RowMapper<Book> {

        @Override
        public Book mapRow(ResultSet resultSet, int i) throws SQLException {
            long id = resultSet.getLong("id");
            String title = resultSet.getString("title");
            long authorID = resultSet.getLong("AuthorID");
            String authorName = resultSet.getString("AuthorName");
            String authorSurname = resultSet.getString("AuthorSurname");
            long genreID = resultSet.getLong("GenreID");
            String genreDescription = resultSet.getString("GenreDescription");
            Author author = new Author(authorID, authorName, authorSurname);
            Genre genre = new Genre(genreID, genreDescription);
            return new Book(id, title, author, genre);
        }
    }
}
