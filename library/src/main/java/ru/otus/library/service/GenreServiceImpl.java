package ru.otus.library.service;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import ru.otus.library.domain.Genre;
import ru.otus.library.repository.GenreRepository;
import ru.otus.library.rest.NotFoundException;
import ru.otus.library.security.IsViewer;

import java.util.ArrayList;
import java.util.List;

@AllArgsConstructor
@Service
public class GenreServiceImpl implements GenreService {
    private final GenreRepository genreRepository;

    public Genre getGenreById(String id) {
        Genre genre = genreRepository.findById(id).orElseThrow(NotFoundException::new);
        return genre;
    }

    public List<Genre> getAllGenres() {
        List<Genre> result = genreRepository.findAll();
        if (result.size() > 0) {
            return result;
        } else {
            return new ArrayList<Genre>();
        }
    }

    public Genre saveGenre(String description) {
        if (!genreRepository.getByDescription(description).isPresent()) {
            Genre genre = new Genre();
            genre.setDescription(description);
            genreRepository.save(genre);
            return genre;
        } else {
            return genreRepository.getByDescription(description).get();
        }
    }

    @IsViewer
    public Genre updateGenre(Genre entity) {
        Genre editedGenre = genreRepository.findById(entity.getId()).get();
        editedGenre.setDescription(entity.getDescription());
        editedGenre = genreRepository.save(editedGenre);
        return editedGenre;
    }

    @IsViewer
    public void deleteGenre(String id) {
        genreRepository.deleteById(id);
    }
}
