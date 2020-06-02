package ru.otus.library;

import lombok.val;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;

import org.springframework.context.annotation.Import;
import org.springframework.dao.EmptyResultDataAccessException;
import ru.otus.library.dao.AuthorDaoORM;
import ru.otus.library.dao.BookDaoORM;
import ru.otus.library.dao.CommentDaoORM;
import ru.otus.library.dao.GenreDaoORM;
import ru.otus.library.domain.Author;
import ru.otus.library.domain.Book;
import ru.otus.library.domain.Genre;

import static org.assertj.core.api.Assertions.assertThat;

@DisplayName("Dao by jpa is for work with books")
@DataJpaTest
@Import({BookDaoORM.class, AuthorDaoORM.class, GenreDaoORM.class, CommentDaoORM.class})
class LibraryApplicationTest {

    @Autowired
    private TestEntityManager em;

    @Autowired
    BookDaoORM bookDao;

    @Autowired
    GenreDaoORM genreDao;

    @Autowired
    AuthorDaoORM authorDao;

    @Autowired
    CommentDaoORM commentDao;

    @DisplayName("Should return book by id")
    @Test
    void shouldReturnBookById() {
        val optionalActualBook = bookDao.getById(1L);
        val expectedBook = em.find(Book.class, 1L);
        assertThat(optionalActualBook).isPresent().get()
                .isEqualToComparingFieldByField(expectedBook);
    }

    @DisplayName("Should return all books with full iformation about them")
    @Test
    void shouldReturnCorrectBooksListWithAllInfo() {
        val books = bookDao.getAll();
        assertThat(books).isNotNull().hasSize(2)
                .allMatch(s -> !s.getTitle().equals(""))
                .allMatch(s -> s.getAuthors() != null && s.getAuthors().size() > 0)
                .allMatch(s -> s.getGenre() != null);
    }

    @DisplayName("Should return all comments for given book")
    @Test
    void shouldReturnCommentsForBook() {
        val book = em.find(Book.class, 1L);
        val comments = commentDao.getAll(book);
        assertThat(comments.get()).isNotNull().hasSize(3)
                .allMatch(s -> !s.getText().equals(""));
    }

    @DisplayName("Should return created book")
    @Test
    void shouldReturnCreatedBookById() {
        val author = new Author();
        author.setName("Gianni");
        author.setSurname("Rodari");
        val genre = new Genre();
        genre.setDescription("Fairytales");
        val book = new Book();
        book.setTitle("Chipolino");
        book.addAuthor(author);
        book.setGenre(genre);
        bookDao.insert(book);
        val result = em.find(Book.class, 3L);
        assertThat(result).isEqualToComparingFieldByField(book);
    }

    @DisplayName("Change book title")
    @Test
    void shouldReturnChangedBook() {
        String titleExpected = "Invincible";
        String oldTitle = em.find(Book.class, 1L).getTitle();
        bookDao.update(1L, "Invincible");
        em.clear();
        String newTitle = em.find(Book.class, 1L).getTitle();
        assertThat(newTitle).isNotEqualTo(oldTitle).isEqualTo(titleExpected);
    }

    @DisplayName("Should return null after book deletion")
    @Test
    void WhenDeleteBook() throws EmptyResultDataAccessException {
        bookDao.deleteById(2L);
        em.clear();
        Book deletedBook = em.find(Book.class, 2L);
        assertThat(deletedBook).isNull();
    }
}
