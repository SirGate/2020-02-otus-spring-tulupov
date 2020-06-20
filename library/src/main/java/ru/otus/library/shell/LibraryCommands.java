package ru.otus.library.shell;

import lombok.AllArgsConstructor;
import org.springframework.shell.standard.ShellComponent;
import org.springframework.shell.standard.ShellMethod;
import ru.otus.library.dao.AuthorDao;
import ru.otus.library.dao.BookDao;
import ru.otus.library.dao.CommentDao;
import ru.otus.library.dao.GenreDao;
import ru.otus.library.domain.Author;
import ru.otus.library.domain.Book;
import ru.otus.library.domain.Comment;
import ru.otus.library.domain.Genre;

import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@ShellComponent
@AllArgsConstructor
public class LibraryCommands {
    private final GenreDao genreDao;
    private final BookDao bookDao;
    private final AuthorDao authorDao;
    private final CommentDao commentDao;


    @ShellMethod(value = "Create Genre", key = "create genre")
    public void insertGenre(String description) {
        Genre genre = new Genre();
        genre.setDescription(description);
        genreDao.insert(genre);
    }

    @ShellMethod(value = "Update Genre description", key = "edit genre")
    public void editGenre(String descriptionOld, String descriptionNew) {
        if (genreDao.getByDescription(descriptionOld).isPresent()) {
            Genre genre = genreDao.getByDescription(descriptionOld).get();
            genreDao.update(genre, descriptionNew);
        }
    }

    @ShellMethod(value = "Delete Genre by description", key = "delete genre")
    public void deleteGenre(String description) {
        genreDao.deleteByDescription(description);
    }

    @ShellMethod(value = "Show all genres", key = "getAllG")
    public void getAllG() {
        System.out.println("All count " + genreDao.count());
        for (Genre genre : genreDao.getAll()) {
            System.out.println("Genre id: " + genre.getId() + " description: " + genre.getDescription());
        }
    }

    @ShellMethod(value = "Create Author", key = "create author")
    public void insertAuthor(String name, String surname) {
        Author author = new Author();
        author.setName(name);
        author.setSurname(surname);
        authorDao.insert(author);
    }

    @ShellMethod(value = "Update Author's Surname and Name", key = "edit author")
    public void editAuthor(String nameOld, String surnameOld, String nameNew, String surnameNew) {
        if (authorDao.getBySurnameAndName(nameOld, surnameOld).isPresent()) {
            Author author = authorDao.getBySurnameAndName(nameOld, surnameOld).get();
            authorDao.update(author, nameNew, surnameNew);
        }
    }

    @ShellMethod(value = "Delete Author by name and surname", key = "delete author")
    public void deleteAuthor(String name, String surname) {
        authorDao.deleteBySurnameAndName(name, surname);
    }

    @ShellMethod(value = "Show all Authors", key = "getAllA")
    public void getAllA() {
        System.out.println("All count " + authorDao.count());
        for (Author author : authorDao.getAll()) {
            System.out.println("Author id: " + author.getId() + " " + "Name: " + author.getName() + " " + author.getSurname());
        }
    }

    @ShellMethod(value = "Show all books in the library", key = "getAllB")
    public void getAllB() {
        System.out.println("All count " + bookDao.count());
        for (Book book : bookDao.getAll()) {
            System.out.println("Book id: " + book.getId() + " title: " + book.getTitle() + " " + book.getAuthors().toString() +
                    " " + book.getGenre().toString());
        }
    }

    @ShellMethod(value = "Create Book", key = "create book")
    public void insertBook(String title, String authorName, String authorsurname, String genreDescription) {
        Genre genre = null;
        Author author = null;
        if (genreDao.getByDescription(genreDescription).isPresent()) {
            genre = genreDao.getByDescription(genreDescription).get();
        } else {
            genre = new Genre();
            genre.setDescription(genreDescription);
            genreDao.insert(genre);
        }
        if (authorDao.getBySurnameAndName(authorName, authorsurname).isPresent()) {
            author = authorDao.getBySurnameAndName(authorName, authorsurname).get();
        } else {
            author = new Author();
            author.setName(authorName);
            author.setSurname(authorsurname);
            authorDao.insert(author);
        }
        Book book = new Book();
        book.setTitle(title);
        book.addAuthor(author);
        book.setGenre(genre);
        bookDao.insert(book);
    }

    @ShellMethod(value = "add additional author", key = "add author")
    public void insertAuthor(long id, String authorName, String authorsurname) {
        Author author = null;
        Book book = null;
        if (bookDao.getById(id).isPresent()) {
            book = bookDao.getById(id).get();
        }
        if (authorDao.getBySurnameAndName(authorName, authorsurname).isPresent()) {
            author = authorDao.getBySurnameAndName(authorName, authorsurname).get();
        }
        if (author == null) {
            author = new Author();
            author.setName(authorName);
            author.setSurname(authorsurname);
            authorDao.insert(author);
        }
        book.addAuthor(author);
        bookDao.insert(book);
    }

    @ShellMethod(value = "Update book title", key = "edit book")
    public void editByTitle(String title, long id, String titleNew) {
        if (bookDao.getById(id).isPresent()) {
            bookDao.update(id, titleNew);
        }
    }

    @ShellMethod(value = "Delete by id", key = "delete book")
    public void deleteByTitle(long id) {
        if (bookDao.getById(id).isPresent()) {
            Book book = bookDao.getById(id).get();
            bookDao.deleteById(id);
        }
    }

    @Transactional
    @ShellMethod(value = "Show all coments for book", key = "getAllC")
    public void getAllComments(long id) {
        if (bookDao.getById(id).isPresent()) {
            List<Comment> comments = bookDao.getById(id).get().getComments();
            for (Comment comment : comments) {
                System.out.println("Comment id: " + comment.getId() + "  " + comment.getText());
            }
        }
    }

    @ShellMethod(value = "write Comment", key = "write comment")
    public void insertComment(long id, String text) {
        Book book = null;
        if (bookDao.getById(id).isPresent()) {
            book = bookDao.getById(id).get();
            Comment comment = new Comment();
            comment.setText(text);
            comment.setBook(book);
            commentDao.insert(comment);
        }
    }
}
