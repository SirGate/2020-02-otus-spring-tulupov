package otus.dao;


import otus.domain.Question;
import otus.exc.QuestionsLoadException;

import java.util.List;

public interface QuestionDao {
    List<Question> getNewQuestions() throws QuestionsLoadException;
 }
