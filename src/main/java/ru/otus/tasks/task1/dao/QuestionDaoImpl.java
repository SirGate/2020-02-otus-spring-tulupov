package ru.otus.tasks.task1.dao;

import ru.otus.tasks.task1.domain.Person;
import ru.otus.tasks.task1.domain.Question;
import ru.otus.tasks.task1.service.IOService;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;

public class QuestionDaoImpl implements QuestionDao {

   private final String ask;
   private final String answer;

    public QuestionDaoImpl(String ask, String answer) {
        this.ask = ask;
        this.answer = answer;
    }

    @Override
    public Question getNewQuestion() {
           Question question = new Question(ask, answer);
        return question;
    }

    @Override
    public int check(Person person, String path, IOService ioService) {
        String issue;
        int result = 0;
        try {
            InputStream i = this.getClass().getClassLoader().getResourceAsStream(path);
            BufferedReader fileInput = new BufferedReader(new InputStreamReader(i));
            while ((issue = fileInput.readLine()) != null) {
                String[] questions =  issue.split(",");
                QuestionDaoImpl fetch = new QuestionDaoImpl(questions[0], questions[1]);
                Question question = fetch.getNewQuestion();
                int answer = ioService.askInt(question.getAsk());
                if (answer == Integer.valueOf(question.getAnswer())) {
                    result++;
                }
                ioService.print("Правильный ответ: " + question.getAnswer() + System.lineSeparator());
            }
            i.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return result;
    }
}
