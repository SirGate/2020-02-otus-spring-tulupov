package ru.otus.library.shell;

import lombok.AllArgsConstructor;
import lombok.val;
import org.springframework.shell.standard.ShellComponent;
import org.springframework.shell.standard.ShellMethod;
import ru.otus.library.domain.Book;
import ru.otus.library.domain.Comment;

import ru.otus.library.repository.BookRepository;
import ru.otus.library.repository.CommentRepository;
import ru.otus.library.service.Input;

import java.util.List;

@ShellComponent
@AllArgsConstructor
public class CommentCommands {
    private final CommentRepository commentRepository;
    private final BookRepository bookRepository;
    private final Input consoleInput;

    @ShellMethod(value = "Show all coments for book", key = "getAllC")
    public void getAllComments(String bookTitle) {
        Book book = null;
        if (bookRepository.findByTitle(bookTitle).isPresent()) {
            val books = bookRepository.findByTitle(bookTitle).get();
            int index = 1;
            for (Book item : books) {
                System.out.println(index + " " + " title: " + item.getTitle()
                        + " " + item.getAuthors().toString() +
                        " " + item.getGenre().toString());
                index++;
            }
            if (books.size() > 0) {
                int entered = consoleInput.askInt(
                        "Please input index number of a book which comments you want to see or press 'c' to cancel:",
                        books.size());
                if (entered > 0) {
                    book = bookRepository.findById(books.get(entered - 1).getId()).get();
                    List<Comment> comments = book.getComments();
                    for (Comment comment : comments) {
                        System.out.println("Comment id: " + comment.getId() + "  " + comment.getText());
                    }
                }
            } else {
                System.out.println("Книги с таким названием нет.");
            }
        }
    }

    @ShellMethod(value = "write Comment by book title", key = "write comment")
    public void saveComment(String bookTitle, String text) {
        Book book = null;
        if (bookRepository.findByTitle(bookTitle).isPresent()) {
            val books = bookRepository.findByTitle(bookTitle).get();
            int index = 1;
            for (Book item : books) {
                System.out.println(index + " " + " title: " + item.getTitle()
                        + " " + item.getAuthors().toString() +
                        " " + item.getGenre().toString());
                index++;
            }
            if (books.size() > 0) {
                int entered = consoleInput.askInt(
                        "Please input index number of the commented book or press 'c' to cancel:",
                        books.size());
                if (entered > 0) {
                    book = bookRepository.findById(books.get(entered - 1).getId()).get();
                    Comment comment = new Comment();
                    comment.setText(text);
                    book.addComment(comment);
                    commentRepository.save(comment);
                    bookRepository.save(book);
                }
            }
        } else {
            System.out.println("Книги с таким названием нет.");
        }
    }
}

