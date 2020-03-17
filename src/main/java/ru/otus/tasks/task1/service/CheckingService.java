package ru.otus.tasks.task1.service;

import ru.otus.tasks.task1.domain.Question;

import java.util.List;

public interface CheckingService {
    int check(List<Question> questions);
}
