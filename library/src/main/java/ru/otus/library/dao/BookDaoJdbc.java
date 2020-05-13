package ru.otus.library.dao;

import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcOperations;
import org.springframework.stereotype.Repository;
import ru.otus.library.domain.Author;
import ru.otus.library.domain.Book;
import ru.otus.library.domain.Genre;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Repository
public class BookDaoJdbc implements BookDao {
    private final NamedParameterJdbcOperations jdbc;

    public BookDaoJdbc(NamedParameterJdbcOperations jdbc) {
        this.jdbc = jdbc;
    }

    @Override
    public int count() {
        return jdbc.getJdbcOperations().queryForObject("select count(*) from books", null, Integer.class);
    }

    @Override
    public void insert(Book book) {
        jdbc.getJdbcOperations().
                update("insert into books (id, `title`, `authorID`, `authorName`, `authorSurname`," +
                                "`genreID`, `genreDescription`) values (?, ?, ?, ?, ?, ?, ?)",
                        book.getId(), book.getTitle(), book.getAuthor().getId(), book.getAuthor().getName(),
                        book.getAuthor().getSurname(), book.getGenre().getId(), book.getGenre().getDescription());
    }

    @Override
    public void update(Author author, String titleNew) {
        jdbc.getJdbcOperations().
                update("update books set `title` = ? where `authorName` = ? and `authorSurname` = ?",
                        titleNew, author.getName(), author.getSurname()
                );
    }

    @Override
    public Book getById(long id) {
        Map<String, Object> params = Collections.singletonMap("id", id);
        return jdbc.queryForObject(
                "select * from books where id = :id", params, new BookMapper()
        );
    }

    @Override
    public List<Book> getAll() {
        return jdbc.query("select * from books", new BookMapper());
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
