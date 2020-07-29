package ru.otus.library;

import lombok.val;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import ru.otus.library.repository.AuthorRepository;
import ru.otus.library.repository.BookRepository;
import ru.otus.library.repository.GenreRepository;

import static org.assertj.core.api.Assertions.assertThat;

@DisplayName("Spring Data jpa works with books")
@DataJpaTest
class LibraryApplicationTest {

    @Autowired
    BookRepository bookRepository;

    @Autowired
    AuthorRepository authorRepository;


    @Autowired
    GenreRepository genreRepository;

    @DisplayName("Should return genre by description")
    @Test
    void shouldReturnGenreByDescription() {
        val genre = genreRepository.getByDescription("Fairytales").get();
        assertThat(genre.getId()).isEqualTo(2L);
    }

    @DisplayName("Should return Author by Surname and Name")
    @Test
    void shouldReturnAuthorBySurnameAndName() {
        val author = authorRepository.
                findBySurnameAndName("Twain", "Mark").get();
        assertThat(author.getId()).isEqualTo(3L);
    }

   /* @DisplayName("Should return all comments for given book")
    @Test
    void shouldReturnCommentsForBook() {
        val comments = bookRepository.findById(1).get().getComments();
        assertThat(comments).isNotNull().hasSize(3)
                .allMatch(s -> !s.getText().equals(""));
    }*/
}
