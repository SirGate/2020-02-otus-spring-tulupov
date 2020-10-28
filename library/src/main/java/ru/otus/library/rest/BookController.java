package ru.otus.library.rest;

import lombok.AllArgsConstructor;
import lombok.val;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import ru.otus.library.domain.Book;
import ru.otus.library.service.AuthorService;
import ru.otus.library.service.BookService;
import ru.otus.library.service.GenreService;

@AllArgsConstructor
@Controller
public class BookController {

    private final BookService bookService;
    private final GenreService genreService;
    private final AuthorService authorService;

    @GetMapping("/")
    public String mainPage(Model model) {
        return "index";
    }

    @GetMapping("/list_books")
    public String listPage(Model model) {
        model.addAttribute("books", bookService.getAllBooks());
        return "list_books";
    }

    @GetMapping("/create")
    public String createPage(Book book, Model model) {
        model.addAttribute("book", book);
        return "create-book";
    }

    @PostMapping("/create")
    public String createBook(Book book, String name, String surname, String genre, Model model) {
        val thisGenre = genreService.saveGenre(genre);
        val author = authorService.saveAuthor(name, surname);
        book.addAuthor(author);
        book.setGenre(thisGenre);
        model.addAttribute("book", bookService.createBook(book));
        return "info-book";
    }

    @GetMapping("/edit_book")
    public String editPage(@RequestParam("id") String id, Model model) {
        model.addAttribute("book", bookService.getBookById(id));
        return "edit_book";
    }

    @PostMapping("/edit_book")
    public String saveBook(
            Book book,
            Model model
    ) {
        model.addAttribute(bookService.updateBook(book));
        return "info-book";
    }

    @GetMapping("/delete-book")
    public String deletePage(@RequestParam("id") String id) {
        bookService.deleteBook(id);
        return "delete-book";
    }
}
