package ru.otus.task3.mathtest.service;

import ru.otus.task3.mathtest.domain.Question;

import java.util.List;

public interface CheckingService {
    int check(List<Question> questions);
}
