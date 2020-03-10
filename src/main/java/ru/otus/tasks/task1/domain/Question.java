package ru.otus.tasks.task1.domain;

public class Question {

  private final String ask;
  private final String answer;

    public Question(String ask, String answer) {
        this.ask = ask;
        this.answer = answer;
    }

    public String getAsk() {
        return ask;
    }

    public String getAnswer() {
        return answer;
    }
}
