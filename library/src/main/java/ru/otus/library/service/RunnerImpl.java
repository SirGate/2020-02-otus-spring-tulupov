package ru.otus.library.service;

import org.springframework.stereotype.Service;
import ru.otus.library.dao.AuthorDao;
import ru.otus.library.dao.BookDao;
import ru.otus.library.dao.GenreDao;

@Service
public class RunnerImpl implements Runner {
    private final GenreDao genreDao;
    private final BookDao bookDao;
    private final AuthorDao authorDao;

    public RunnerImpl(GenreDao genreDao, BookDao bookDao, AuthorDao authorDao) {
        this.genreDao = genreDao;
        this.bookDao = bookDao;
        this.authorDao = authorDao;
    }

    @Override
    public void startLibrary() {
    }
}
