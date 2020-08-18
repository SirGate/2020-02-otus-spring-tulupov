package ru.otus.library.shell;

import lombok.AllArgsConstructor;
import lombok.val;
import org.springframework.shell.standard.ShellComponent;
import org.springframework.shell.standard.ShellMethod;
import ru.otus.library.domain.Author;
import ru.otus.library.domain.Book;
import ru.otus.library.domain.Genre;

import ru.otus.library.repository.AuthorRepository;
import ru.otus.library.repository.BookRepository;
import ru.otus.library.repository.GenreRepository;
import ru.otus.library.service.Input;

@ShellComponent
@AllArgsConstructor
public class BookCommands {
    private final GenreRepository genreRepository;
    private final BookRepository bookRepository;
    private final AuthorRepository authorRepository;
    private final Input consoleInput;

    @ShellMethod(value = "Show all books in the library", key = "getAllB")
    public void getAllB() {
        System.out.println("All count " + bookRepository.count());
        for (Book book : bookRepository.findAll()) {
            System.out.println("Book id: " + book.getId() + " title: " + book.getTitle()
                    + " " + book.getAuthors().toString() +
                    " " + book.getGenre().toString());
        }
    }

    @ShellMethod(value = "Create Book", key = "create book")
    public void saveBook(String title, String authorName, String authorSurname, String genreDescription) {
        Genre genre = null;
        Author author = null;
        genre = new Genre();
        genre.setDescription(genreDescription);
        if (genreRepository.getByDescription(genreDescription).isPresent()) {
            genre.setId(genreRepository.getByDescription(genreDescription).get().getId());
        }
        author = new Author();
        author.setName(authorName);
        author.setSurname(authorSurname);
        if (authorRepository.findBySurnameAndName(authorSurname, authorName).isPresent()) {
            author.setId(authorRepository.findBySurnameAndName(authorSurname, authorName).get().getId());
        }
        Book book = new Book();
        book.setTitle(title);
        book.addAuthor(author);
        book.setGenre(genre);
        Book tempBook = bookRepository.save(book);
        author.addBook(bookRepository.findById(tempBook.getId()).get());
        author = authorRepository.findBySurnameAndName(authorSurname, authorName).get();
        author.addBook(tempBook);
        authorRepository.save(author);
    }

    @ShellMethod(value = "add additional author", key = "add author")
    public void saveAuthor(String title, String authorName, String authorsurname) {
        Author author = null;
        Book book = null;
        if (bookRepository.findByTitle(title).isPresent()) {
            val books = bookRepository.findByTitle(title).get();
            int index = 1;
            for (Book item : books) {
                System.out.println(index + " " + " title: " + item.getTitle()
                        + " " + item.getAuthors().toString() +
                        " " + item.getGenre().toString());
                index++;
            }
            if (books.size() > 0) {
                int entered = consoleInput.askInt(
                        "Please input index number of edited book or press 'c' to cancel:",
                        books.size());
                if (entered > 0) {
                    book = bookRepository.findById(books.get(entered - 1).getId()).get();
                    if (authorRepository.findBySurnameAndName(authorsurname, authorName).isPresent()) {
                        author = authorRepository.findBySurnameAndName(authorsurname, authorName).get();
                    }
                    if (author == null) {
                        author = new Author();
                        author.setName(authorName);
                        author.setSurname(authorsurname);
                        authorRepository.save(author);
                    }
                    if (!book.getAuthor(author)) {
                        book.addAuthor(author);
                        bookRepository.save(book);
                    }
                }
            }
        } else {
            System.out.println("Книги с таким названием нет.");
        }
    }

    @ShellMethod(value = "Update book title", key = "edit book")
    public void editByTitle(String title, String titleNew) {
        if (bookRepository.findByTitle(title).isPresent()) {
            val books = bookRepository.findByTitle(title).get();
            int index = 1;
            for (Book item : books) {
                System.out.println(index + " " + " title: " + item.getTitle()
                        + " " + item.getAuthors().toString() +
                        " " + item.getGenre().toString());
                index++;
            }
            if (books.size() > 0) {
                int entered = consoleInput.askInt(
                        "Please input index number of edited book or press 'c' to cancel:",
                        books.size());
                if (entered > 0) {
                    Book book = bookRepository.findById(books.get(entered - 1).getId()).get();
                    book.setTitle(titleNew);
                    bookRepository.save(book);
                }
            }
        } else {
            System.out.println("Книги с таким названием нет.");
        }
    }

    @ShellMethod(value = "Delete by title", key = "delete book")
    public void deleteByTitle(String title) {
        if (bookRepository.findByTitle(title).isPresent()) {
            val books = bookRepository.findByTitle(title).get();
            int index = 1;
            for (Book book : books) {
                System.out.println(index + " " + " title: " + book.getTitle()
                        + " " + book.getAuthors().toString() +
                        " " + book.getGenre().toString());
                index++;
            }
            if (books.size() > 0) {
                int entered = consoleInput.askInt(
                        "Please input index number of deleted book or press 'c' to cancel:",
                        books.size());
                if (entered > 0) {
                    bookRepository.deleteById(books.get(entered - 1).getId());
                }
            }
        } else {
            System.out.println("Книги с таким названием нет.");
        }
    }
}
