package ru.otus.library.rest;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import ru.otus.library.domain.Genre;
import ru.otus.library.service.GenreService;

@AllArgsConstructor
@Controller
public class GenreController {

    private final GenreService genreService;

    @GetMapping("/list_genres")
    public String listPage(Model model) {
        model.addAttribute("genres", genreService.getAllGenres());
        return "list_genres";
    }

    @GetMapping("/edit-genre")
    public String editPage(@RequestParam("id") String id, Model model) {
        model.addAttribute("genre", genreService.getGenreById(id));
        return "edit-genre";
    }

    @PostMapping("/edit-genre")
    public String saveGenre(
            Genre genre,
            Model model
    ) {
        model.addAttribute(genreService.updateGenre(genre));
        return "info-genres";
    }

    @GetMapping("/delete-genre")
    public String deletePage(@RequestParam("id") String id) {
        genreService.deleteGenre(id);
        return "delete-genre";
    }
}

