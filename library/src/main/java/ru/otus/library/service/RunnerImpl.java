package ru.otus.library.service;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import ru.otus.library.dao.BookDao;
import ru.otus.library.dao.CommentDao;
import ru.otus.library.dao.GenreDao;
import ru.otus.library.repository.AuthorRepository;

@Service
@AllArgsConstructor
public class RunnerImpl implements Runner {
    private final GenreDao genreDao;
    private final BookDao bookDao;
    private final AuthorRepository authorRepository;
    private final CommentDao commentDao;

    @Override
    public void startLibrary() {
    }
}
