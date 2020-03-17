package ru.otus.tasks.task1.dao;

import ru.otus.tasks.task1.domain.Question;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;

public class QuestionDaoImpl implements QuestionDao {
    private final String path;

   private final List<Question> questions = new ArrayList<>();

    public QuestionDaoImpl(String path) {
        this.path = path;
    }

    @Override
    public List<Question> getNewQuestions() throws IOException {
         String issue;
         try {
             InputStream i = this.getClass().getClassLoader().getResourceAsStream(path);
             BufferedReader fileInput = new BufferedReader(new InputStreamReader(i));
             while ((issue = fileInput.readLine()) != null) {
                 String[] fullQuestion =  issue.split(",");
                 Question question = new Question(fullQuestion[0], fullQuestion[1]);
                 questions.add(question);
             }
             i.close();
         } catch (Exception e) {
             throw new IOException();
         }
         return questions;
  }
}
