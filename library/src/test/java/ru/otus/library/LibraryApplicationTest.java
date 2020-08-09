package ru.otus.library;

import lombok.val;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.boot.test.autoconfigure.data.mongo.DataMongoTest;

import org.springframework.context.annotation.ComponentScan;
import ru.otus.library.domain.Author;
import ru.otus.library.domain.Book;
import ru.otus.library.domain.Comment;
import ru.otus.library.domain.Genre;
import ru.otus.library.repository.AuthorRepository;
import ru.otus.library.repository.BookRepository;
import ru.otus.library.repository.CommentRepository;
import ru.otus.library.repository.GenreRepository;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertAll;


@DataMongoTest
@EnableConfigurationProperties
@ComponentScan({"ru.otus.library"})
@DisplayName("Spring Data Mongo works with books")
class LibraryApplicationTest {

    @Autowired
    BookRepository bookRepository;

    @Autowired
    AuthorRepository authorRepository;

    @Autowired
    GenreRepository genreRepository;

    @Autowired
    CommentRepository commentRepository;


    @DisplayName("Should return all comments for given book")
    @Test
    void shouldReturnCommentsForBook() {
        if (bookRepository.findAll().size() == 0) {
            val author = authorRepository.
                    findBySurnameAndName("Lem", "Stanislav").get();
            val genre = genreRepository.getByDescription("Science fiction").get();
            Book book = new Book("Solaris", genre, author);
            Comment comment1 = new Comment();
            comment1.setText("It's a good book");
            book.addComment(comment1);
            bookRepository.save(book);
            commentRepository.save(comment1);
            Comment comment2 = new Comment();
            comment2.setText("It's a bad book");
            book.addComment(comment2);
            bookRepository.save(book);
            commentRepository.save(comment2);
        }
        val book = bookRepository.findByTitle("Solaris").get().get(0);
        List<Comment> comments = book.getComments();
        assertThat(comments).isNotNull().hasSize(2)
                .allMatch(s -> !s.getText().equals(""));
    }

    @DisplayName("Should return created author and created genre for created book")
    @Test
    void shouldReturnCreatedAuthorAndCreatedGenreAfterBookCreation() {
        val author = new Author("Gianni", "Rodari");
        val genre = new Genre("Fairytales");
        val book = new Book("Chippolino", genre, author);
        bookRepository.save(book);
        val expectedAuthor = authorRepository.
                findBySurnameAndName("Rodari", "Gianni").get();
        val expectedGenre = genreRepository.getByDescription("Fairytales").get();
        assertAll(
                () -> assertThat(expectedGenre.getDescription()).isEqualTo("Fairytales"),
                () -> assertThat(expectedAuthor.getName()).isEqualTo("Gianni"),
                () -> assertThat(expectedAuthor.getSurname()).isEqualTo("Rodari")
        );
    }

    @DisplayName("Should delete book inside author and all comments after book deletion")
    @Test
    void shouldDeleteBookinsideAuthorAndAllCommentsAfterBookDeletion() {
        val book = bookRepository.findByTitle("Solaris").get().get(0);
        bookRepository.deleteById(book.getId());
        int expectedSize = authorRepository.
                findBySurnameAndName("Lem", "Stanislav").get().getBooks().size();
        assertAll(
                () -> assertThat(commentRepository.findAll()).isEmpty(),
                () -> assertThat(expectedSize).isEqualTo(0)
        );
    }

}
