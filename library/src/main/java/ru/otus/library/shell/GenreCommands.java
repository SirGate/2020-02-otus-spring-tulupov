package ru.otus.library.shell;

import lombok.AllArgsConstructor;
import org.springframework.shell.standard.ShellComponent;
import org.springframework.shell.standard.ShellMethod;
import ru.otus.library.domain.Genre;

import ru.otus.library.repository.GenreRepository;


@ShellComponent
@AllArgsConstructor
public class GenreCommands {
    private final GenreRepository genreRepository;

    @ShellMethod(value = "Create Genre", key = "create genre")
    public void saveGenre(String description) {
        if (!genreRepository.getByDescription(description).isPresent()) {
            Genre genre = new Genre();
            genre.setDescription(description);
            genreRepository.save(genre);
        }
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
            String id = genreRepository.getByDescription(description).get().getId();
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
}
