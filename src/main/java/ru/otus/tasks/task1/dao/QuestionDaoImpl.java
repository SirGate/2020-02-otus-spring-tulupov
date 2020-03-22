package ru.otus.tasks.task1.dao;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;
import ru.otus.tasks.task1.config.LocaleProps;
import ru.otus.tasks.task1.domain.Question;
import ru.otus.tasks.task1.exc.QuestionsLoadException;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;

@RequiredArgsConstructor
@Repository
public class QuestionDaoImpl implements QuestionDao {

    private final LocaleProps localeProps;

   private final List<Question> questions = new ArrayList<>();

    @Override
    public List<Question> getNewQuestions() throws QuestionsLoadException {
         String issue;
           try {
             InputStream i = this.getClass().getClassLoader().getResourceAsStream(localeProps.getQuestionsFile());
             BufferedReader fileInput = new BufferedReader(new InputStreamReader(i));
             while ((issue = fileInput.readLine()) != null) {
                 String[] fullQuestion =  issue.split(",");
                 Question question = new Question(fullQuestion[0], fullQuestion[1]);
                 questions.add(question);
             }
             i.close();
         } catch (Exception e) {
             throw new QuestionsLoadException(e);
         }
         return questions;
  }
}
