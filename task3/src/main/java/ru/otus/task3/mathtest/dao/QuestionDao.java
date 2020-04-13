package ru.otus.task3.mathtest.dao;

import ru.otus.task3.mathtest.domain.Question;
import ru.otus.task3.mathtest.exc.QuestionsLoadException;

import java.util.List;

public interface QuestionDao {
    List<Question> getNewQuestions() throws QuestionsLoadException;
 }
