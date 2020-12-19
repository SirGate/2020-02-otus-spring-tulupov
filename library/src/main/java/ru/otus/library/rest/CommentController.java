package ru.otus.library.rest;

import lombok.AllArgsConstructor;
import lombok.val;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import ru.otus.library.domain.Comment;
import ru.otus.library.repository.BookRepository;

import java.util.List;

@AllArgsConstructor
@RestController
public class CommentController {

    @Autowired
    BookRepository bookRepository;

    @GetMapping("/comments/{id}")
    public List<Comment> getComments(@PathVariable String id) {
        val book = bookRepository.findById(id).get();
        List<Comment> comments = book.getComments();
        return comments;
    }

    @GetMapping("/book/{id}")
    public String getTitle(@PathVariable String id) {
        val title = bookRepository.findById(id).get().getTitle();
        return title;
    }
}
