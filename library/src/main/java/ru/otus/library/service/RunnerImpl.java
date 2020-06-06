package ru.otus.library.service;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import ru.otus.library.dao.AuthorDao;
import ru.otus.library.dao.BookDao;
import ru.otus.library.dao.CommentDao;
import ru.otus.library.dao.GenreDao;

@Service
@AllArgsConstructor
public class RunnerImpl implements Runner {
    private final GenreDao genreDao;
    private final BookDao bookDao;
    private final AuthorDao authorDao;
    private final CommentDao commentDao;

    @Override
    public void startLibrary() {
    }
}
