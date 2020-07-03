package ru.otus.library.shell;

import lombok.AllArgsConstructor;
import org.springframework.shell.standard.ShellComponent;
import org.springframework.shell.standard.ShellMethod;
import ru.otus.library.domain.Author;
import ru.otus.library.domain.Book;
import ru.otus.library.domain.Genre;

import ru.otus.library.repository.AuthorRepository;
import ru.otus.library.repository.BookRepository;
import ru.otus.library.repository.GenreRepository;

@ShellComponent
@AllArgsConstructor
public class BookCommands {
    private final GenreRepository genreRepository;
    private final BookRepository bookRepository;
    private final AuthorRepository authorRepository;

    @ShellMethod(value = "Show all books in the library", key = "getAllB")
    public void getAllB() {
        System.out.println("All count " + bookRepository.count());
        for (Book book : bookRepository.findAll()) {
            System.out.println("Book id: " + book.getId() + " title: " + book.getTitle() + " " + book.getAuthors().toString() +
                    " " + book.getGenre().toString());
        }
    }

    @ShellMethod(value = "Create Book", key = "create book")
    public void saveBook(String title, String authorName, String authorsurname, String genreDescription) {
        Genre genre = null;
        Author author = null;
        if (genreRepository.getByDescription(genreDescription).isPresent()) {
            genre = genreRepository.getByDescription(genreDescription).get();
        } else {
            genre = new Genre();
            genre.setDescription(genreDescription);
            genreRepository.save(genre);
        }
        if (authorRepository.findBySurnameAndName(authorName, authorsurname).isPresent()) {
            author = authorRepository.findBySurnameAndName(authorName, authorsurname).get();
        } else {
            author = new Author();
            author.setName(authorName);
            author.setSurname(authorsurname);
            authorRepository.save(author);
        }
        Book book = new Book();
        book.setTitle(title);
        book.addAuthor(author);
        book.setGenre(genre);
        bookRepository.save(book);
    }

    @ShellMethod(value = "add additional author", key = "add author")
    public void saveAuthor(long id, String authorName, String authorsurname) {
        Author author = null;
        Book book = null;
        if (bookRepository.findById(id).isPresent()) {
            book = bookRepository.findById(id).get();
        }
        if (authorRepository.findBySurnameAndName(authorName, authorsurname).isPresent()) {
            author = authorRepository.findBySurnameAndName(authorName, authorsurname).get();
        }
        if (author == null) {
            author = new Author();
            author.setName(authorName);
            author.setSurname(authorsurname);
            authorRepository.save(author);
        }
        book.addAuthor(author);
        bookRepository.save(book);
    }

    @ShellMethod(value = "Update book title", key = "edit book")
    public void editByTitle(String title, long id, String titleNew) {
        if (bookRepository.findById(id).isPresent()) {
            Book book = bookRepository.findById(id).get();
            book.setTitle(titleNew);
            bookRepository.save(book);
        }
    }

    @ShellMethod(value = "Delete by id", key = "delete book")
    public void deleteByTitle(long id) {
        if (bookRepository.findById(id).isPresent()) {
            Book book = bookRepository.findById(id).get();
            bookRepository.deleteById(id);
        }
    }
}
