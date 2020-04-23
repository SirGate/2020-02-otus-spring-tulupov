package ru.otus.task4.mathtest.service;

import ru.otus.task4.mathtest.domain.Question;

import java.util.List;

public interface CheckingService {
    int check(List<Question> questions);
}
