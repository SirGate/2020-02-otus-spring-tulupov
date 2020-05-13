package ru.otus.library;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.shell.jline.InteractiveShellApplicationRunner;
import org.springframework.shell.jline.ScriptShellApplicationRunner;
import ru.otus.library.dao.AuthorDao;
import ru.otus.library.dao.BookDaoJdbc;
import ru.otus.library.dao.GenreDao;
import ru.otus.library.domain.Author;
import ru.otus.library.domain.Book;
import ru.otus.library.domain.Genre;

import static org.assertj.core.api.Assertions.*;


@DisplayName("Dao for work with books")
@SpringBootTest(properties = {
        InteractiveShellApplicationRunner.SPRING_SHELL_INTERACTIVE_ENABLED + "=false",
        ScriptShellApplicationRunner.SPRING_SHELL_SCRIPT_ENABLED + "=false"
})
class LibraryApplicationTests {

    @Autowired
    BookDaoJdbc bookDao;

    @Autowired
    GenreDao genreDao;

    @Autowired
    AuthorDao authorDao;

    @DisplayName("Return book by id")
    @Test
    void shouldReturnBookById() {
        String titleExpected = "Solaris";
        String result = bookDao.getById(1).getTitle();
        assertThat(result).isEqualTo(titleExpected);
    }

    @DisplayName("Create new book")
    @Test
    void shouldReturnCreatedBookById() {
        Author author = authorDao.getBySurnameAndName("Gianni", "Rodari");
        Genre genre = genreDao.getByDescription("Fairytales");
        Book book = new Book(2, "Chipolino", author, genre);
        bookDao.insert(book);
        Book result = bookDao.getById(2);
        assertThat(result).isEqualToComparingFieldByFieldRecursively(book);
    }

    @DisplayName("Change book title")
    @Test
    void shouldReturnChangedBook() {
        String titleExpected = "Invincible";
        Author author = authorDao.getBySurnameAndName("Stanislav", "Lem");
        String oldTitle = bookDao.getById(1).getTitle();
        bookDao.update(author, "Invincible");
        String newTitle = bookDao.getById(1).getTitle();
        assertThat(newTitle).isNotEqualTo(oldTitle).isEqualTo(titleExpected);
    }

    @DisplayName("Delete book")
    @Test
    void shouldntReturnDeletedBook() throws EmptyResultDataAccessException {
        Author author = authorDao.getBySurnameAndName("Mark", "Twain");
        bookDao.deleteByTitleAndAuthor("Huckleberry Finn", author);
        Throwable thrown = catchThrowable(() -> {
            Book result = bookDao.getById(3);
        });
        assertThat(thrown).isInstanceOf(EmptyResultDataAccessException.class);
    }
}
