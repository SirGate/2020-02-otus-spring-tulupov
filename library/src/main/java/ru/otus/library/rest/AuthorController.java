package ru.otus.library.rest;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import ru.otus.library.domain.Author;
import ru.otus.library.service.AuthorService;

@AllArgsConstructor
@Controller
public class AuthorController {

    private final AuthorService authorService;


    @GetMapping("/list-authors")
    public String listPage(Model model) {
        model.addAttribute("authors", authorService.getAllAuthors());
        return "list-authors";
    }

    @GetMapping("/edit_author")
    public String editPage(@RequestParam("id") String id, Model model) {
        model.addAttribute("author", authorService.getAuthorById(id));
        return "edit_author";
    }

    @PostMapping("/edit_author")
    public String saveAuthor(
            Author author,
            Model model
    ) {
        model.addAttribute(authorService.updateAuthor(author));
        return "info-authors";
    }

    @GetMapping("/delete-author")
    public String deletePage(@RequestParam("id") String id) {
        authorService.deleteAuthor(id);
        return "delete-author";
    }
}

