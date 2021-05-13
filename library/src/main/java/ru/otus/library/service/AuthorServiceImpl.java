package ru.otus.library.service;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import ru.otus.library.domain.Author;
import ru.otus.library.repository.AuthorRepository;
import ru.otus.library.rest.NotFoundException;
import ru.otus.library.security.IsViewer;

import java.util.ArrayList;
import java.util.List;

@AllArgsConstructor
@Service
public class AuthorServiceImpl implements AuthorService {
    private final AuthorRepository authorRepository;

    public List<Author> getAllAuthors() {
        List<Author> result = authorRepository.findAll();
        if (result.size() > 0) {
            return result;
        } else {
            return new ArrayList<Author>();
        }
    }

    public Author getAuthorById(String id) {
        Author author = authorRepository.findById(id).orElseThrow(NotFoundException::new);
        return author;
    }

    public Author saveAuthor(String name, String surname) {
        if (!authorRepository.findBySurnameAndName(surname, name).isPresent()) {
            Author author = new Author();
            author.setName(name);
            author.setSurname(surname);
            authorRepository.save(author);
            return author;
        } else {
            return authorRepository.findBySurnameAndName(surname, name).get();
        }
    }

    @IsViewer
    public Author updateAuthor(Author entity) {
        Author editedAuthor = authorRepository.findById(entity.getId()).get();
        editedAuthor.setName(entity.getName());
        editedAuthor.setSurname(entity.getSurname());
        editedAuthor = authorRepository.save(editedAuthor);
        return editedAuthor;
    }

    @IsViewer
    public void deleteAuthor(String id) {
        authorRepository.deleteById(id);
    }
}
