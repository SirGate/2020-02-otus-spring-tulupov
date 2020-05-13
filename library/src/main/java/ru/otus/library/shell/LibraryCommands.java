package ru.otus.library.shell;

import org.springframework.shell.standard.ShellComponent;
import org.springframework.shell.standard.ShellMethod;
import ru.otus.library.dao.AuthorDao;
import ru.otus.library.dao.BookDao;
import ru.otus.library.dao.GenreDao;
import ru.otus.library.domain.Author;
import ru.otus.library.domain.Book;
import ru.otus.library.domain.Genre;

@ShellComponent

public class LibraryCommands {
    private final GenreDao genreDao;
    private final BookDao bookDao;
    private final AuthorDao authorDao;

    public LibraryCommands(GenreDao genreDao, BookDao bookDao, AuthorDao authorDao) {
        this.genreDao = genreDao;
        this.bookDao = bookDao;
        this.authorDao = authorDao;
    }

    @ShellMethod(value = "Create Genre", key = "create genre")
    public void insertGenre(long id, String description) {
        Genre genre = new Genre(id, description);
        genreDao.insert(genre);
    }

    @ShellMethod(value = "Update Genre description", key = "edit genre")
    public void editGenre(String descriptionOld, String descriptionNew) {
        Genre genre = genreDao.getByDescription(descriptionOld);
        genreDao.update(genre, descriptionNew);
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
    public void insertAuthor(long id, String name, String surname) {
        Author author = new Author(id, name, surname);
        authorDao.insert(author);
    }

    @ShellMethod(value = "Update Author's Surname and Name", key = "edit author")
    public void editAuthor(String nameOld, String surnameOld, String nameNew, String surnameNew) {
        Author author = authorDao.getBySurnameAndName(nameOld, surnameOld);
        System.out.println("id: " + author.getId() + "Name: " + author.getName() + " " + author.getSurname());
        authorDao.update(author, nameNew, surnameNew);
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
            System.out.println("Book id: " + book.getId() + " title: " + book.getTitle() + " " + book.getAuthor().toString() +
                    " " + book.getGenre().toString());
        }
    }

    @ShellMethod(value = "Create Book", key = "create book")
    public void insertBook(long id, String title, String authorName, String authorsurname, String genreDescription) {
        Genre genre = genreDao.getByDescription(genreDescription);
        Author author = authorDao.getBySurnameAndName(authorName, authorsurname);
        Book book = new Book(id, title, author, genre);
        bookDao.insert(book);
    }

    @ShellMethod(value = "Update book title", key = "edit book")
    public void editByTitle(String title, String authorName, String authorSurName, String titleNew) {
        Author author = authorDao.getBySurnameAndName(authorName, authorSurName);
        bookDao.update(author, titleNew);
    }

    @ShellMethod(value = "Delete by title and author's name and surname", key = "delete book")
    public void deleteByTitle(String title, String authorName, String authorSurName) {
        Author author = authorDao.getBySurnameAndName(authorName, authorSurName);
        bookDao.deleteByTitleAndAuthor(title, author);
    }
}
