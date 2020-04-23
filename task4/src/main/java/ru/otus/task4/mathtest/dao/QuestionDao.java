package ru.otus.task4.mathtest.dao;

import ru.otus.task4.mathtest.domain.Question;
import ru.otus.task4.mathtest.exc.QuestionsLoadException;

import java.util.List;

public interface QuestionDao {
    List<Question> getNewQuestions() throws QuestionsLoadException;
 }
