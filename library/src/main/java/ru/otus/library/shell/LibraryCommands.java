package ru.otus.library.shell;

import lombok.AllArgsConstructor;
import org.springframework.shell.standard.ShellComponent;
import org.springframework.shell.standard.ShellMethod;
import ru.otus.library.domain.Author;
import ru.otus.library.domain.Book;
import ru.otus.library.domain.Comment;
import ru.otus.library.domain.Genre;

import org.springframework.transaction.annotation.Transactional;
import ru.otus.library.repository.AuthorRepository;
import ru.otus.library.repository.BookRepository;
import ru.otus.library.repository.CommentRepository;
import ru.otus.library.repository.GenreRepository;

import java.util.List;

@ShellComponent
@AllArgsConstructor
public class LibraryCommands {
    private final GenreRepository genreRepository;
    private final BookRepository bookRepository;
    private final AuthorRepository authorRepository;
    private final CommentRepository commentRepository;


    @ShellMethod(value = "Create Genre", key = "create genre")
    public void saveGenre(String description) {
        Genre genre = new Genre();
        genre.setDescription(description);
        genreRepository.save(genre);
    }

    @ShellMethod(value = "Update Genre description", key = "edit genre")
    public void editGenre(String descriptionOld, String descriptionNew) {
        if (genreRepository.getByDescription(descriptionOld).isPresent()) {
            Genre genre = genreRepository.getByDescription(descriptionOld).get();
            genre.setDescription(descriptionNew);
            genreRepository.save(genre);
        }
    }

    @ShellMethod(value = "Delete Genre by description", key = "delete genre")
    public void deleteGenre(String description) {
        if (genreRepository.getByDescription(description).isPresent()) {
            long id = genreRepository.getByDescription(description).get().getId();
            genreRepository.deleteById(id);
        }
    }

    @ShellMethod(value = "Show all genres", key = "getAllG")
    public void getAllG() {
        System.out.println("All count " + genreRepository.count());
        for (Genre genre : genreRepository.findAll()) {
            System.out.println("Genre id: " + genre.getId() + " description: " + genre.getDescription());
        }
    }

    @ShellMethod(value = "Create Author", key = "create author")
    public void saveAuthor(String name, String surname) {
        Author author = new Author();
        author.setName(name);
        author.setSurname(surname);
        authorRepository.save(author);
    }

    @ShellMethod(value = "Update Author's Surname and Name", key = "edit author")
    public void editAuthor(String nameOld, String surnameOld, String nameNew, String surnameNew) {
        if (authorRepository.findBySurnameAndName(surnameOld, nameOld).isPresent()) {
            Author author = authorRepository.findBySurnameAndName(surnameOld, nameOld).get();
            author.setName(nameNew);
            author.setSurname(surnameNew);
            authorRepository.save(author);
        }
    }

    @ShellMethod(value = "Delete Author by name and surname", key = "delete author")
    public void deleteAuthor(String name, String surname) {
        if (authorRepository.findBySurnameAndName(surname, name).isPresent()) {
            long id = authorRepository.findBySurnameAndName(surname, name).get().getId();
            authorRepository.deleteById(id);
        }

    }

    @ShellMethod(value = "Show all Authors", key = "getAllA")
    public void getAllA() {
        System.out.println("All count " + authorRepository.count());
        for (Author author : authorRepository.findAll()) {
            System.out.println("Author id: " + author.getId() + " " + "Name: " + author.getName() + " " + author.getSurname());
        }
    }

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

    @Transactional
    @ShellMethod(value = "Show all coments for book", key = "getAllC")
    public void getAllComments(long id) {
        if (bookRepository.findById(id).isPresent()) {
            List<Comment> comments = bookRepository.findById(id).get().getComments();
            for (Comment comment : comments) {
                System.out.println("Comment id: " + comment.getId() + "  " + comment.getText());
            }
        }
    }

    @ShellMethod(value = "write Comment by book id", key = "write comment")
    public void saveComment(long id, String text) {
        Book book = null;
        if (bookRepository.findById(id).isPresent()) {
            book = bookRepository.findById(id).get();
            Comment comment = new Comment();
            comment.setText(text);
            comment.setBook(book);
            commentRepository.save(comment);
        }
    }
}
