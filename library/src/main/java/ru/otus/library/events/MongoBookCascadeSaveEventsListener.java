package ru.otus.library.events;

import lombok.RequiredArgsConstructor;
import lombok.val;
import org.springframework.data.mongodb.core.mapping.event.AbstractMongoEventListener;
import org.springframework.data.mongodb.core.mapping.event.BeforeConvertEvent;
import org.springframework.stereotype.Component;
import ru.otus.library.domain.Book;
import ru.otus.library.domain.Genre;
import ru.otus.library.repository.AuthorRepository;
import ru.otus.library.repository.GenreRepository;

import java.util.Objects;

@Component
@RequiredArgsConstructor
public class MongoBookCascadeSaveEventsListener extends AbstractMongoEventListener<Book> {

    private final GenreRepository genreRepository;
    private final AuthorRepository authorRepository;

    @Override
    public void onBeforeConvert(BeforeConvertEvent<Book> event) {
        super.onBeforeConvert(event);
        val book = event.getSource();
        if (book.getAuthors() != null) {
            book.getAuthors().stream().filter(e ->
                    !authorRepository.
                            findBySurnameAndName(e.getSurname(), e.getName()).isPresent()).
                    forEach(authorRepository::save);
        }
        if (book.getGenre() != null & Objects.isNull(book.getGenre().getId())) {
            Genre genre = book.getGenre();
            genreRepository.save(genre);
        }
    }
}
