package ru.otus.library.shell;

import lombok.AllArgsConstructor;
import org.springframework.shell.standard.ShellComponent;
import org.springframework.shell.standard.ShellMethod;
import ru.otus.library.domain.Book;
import ru.otus.library.domain.Comment;

import org.springframework.transaction.annotation.Transactional;
import ru.otus.library.repository.BookRepository;
import ru.otus.library.repository.CommentRepository;

import java.util.List;

@ShellComponent
@AllArgsConstructor
public class CommentCommands {
    private final CommentRepository commentRepository;
    private final BookRepository bookRepository;

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
