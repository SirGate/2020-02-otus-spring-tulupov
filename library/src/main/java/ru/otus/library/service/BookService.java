package ru.otus.library.service;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import ru.otus.library.domain.Author;
import ru.otus.library.domain.Book;
import ru.otus.library.domain.Genre;
import ru.otus.library.repository.AuthorRepository;
import ru.otus.library.repository.BookRepository;
import ru.otus.library.repository.GenreRepository;
import ru.otus.library.rest.NotFoundException;

import java.util.ArrayList;
import java.util.List;

@AllArgsConstructor
@Service
public class BookService {
    private final BookRepository bookRepository;
    private final AuthorRepository authorRepository;
    private final GenreRepository genreRepository;

    public List<Book> getAllBooks() {
        List<Book> result = bookRepository.findAll();
        if (result.size() > 0) {
            return result;
        } else {
            return new ArrayList<Book>();
        }
    }

    public Book getBookById(String id) {
        Book book = bookRepository.findById(id).orElseThrow(NotFoundException::new);
        return book;

    }

    public Book createBook(Book entity) {
        Genre genre = null;
        Author author = null;
        genre = new Genre();
        genre.setDescription(entity.getGenre().getDescription());
        if (genreRepository.getByDescription(entity.getGenre().getDescription()).isPresent()) {
            genre.setId(genreRepository.getByDescription(entity.getGenre().getDescription()).get().getId());
        }
        author = new Author();
        author.setName(entity.getAuthors().get(0).getName());
        author.setSurname(entity.getAuthors().get(0).getSurname());
        if (authorRepository.findBySurnameAndName(author.getSurname(), author.getName()).isPresent()) {
            author.setId(authorRepository.findBySurnameAndName(author.getSurname(), author.getName()).get().getId());
        }
        Book book = new Book();
        book.setTitle(entity.getTitle());
        book.addAuthor(author);
        book.setGenre(genre);
        Book tempBook = bookRepository.save(book);
        author.addBook(bookRepository.findById(tempBook.getId()).get());
        author = authorRepository.findBySurnameAndName(author.getSurname(), author.getName()).get();
        author.addBook(tempBook);
        authorRepository.save(author);
        return book;
    }

    public Book updateBook(Book entity) {
        Book editedBook = bookRepository.findById(entity.getId()).get();
        editedBook.setTitle(entity.getTitle());
        editedBook = bookRepository.save(editedBook);
        return editedBook;
    }

    public void deleteBook(String id) {
        bookRepository.deleteById(id);
    }
}