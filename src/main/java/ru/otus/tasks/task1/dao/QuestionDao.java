package ru.otus.tasks.task1.dao;

import ru.otus.tasks.task1.domain.Person;
import ru.otus.tasks.task1.domain.Question;
import ru.otus.tasks.task1.service.IOService;

public interface QuestionDao {
    Question getNewQuestion();
    int check(Person person, String path, IOService ioService);
}
