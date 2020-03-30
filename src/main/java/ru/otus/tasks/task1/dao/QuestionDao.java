package ru.otus.tasks.task1.dao;

import ru.otus.tasks.task1.domain.Question;
import ru.otus.tasks.task1.exc.QuestionsLoadException;

import java.util.List;

public interface QuestionDao {
    List<Question> getNewQuestions() throws QuestionsLoadException;
 }
