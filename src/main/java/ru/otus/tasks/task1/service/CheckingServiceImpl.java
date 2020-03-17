package ru.otus.tasks.task1.service;

import ru.otus.tasks.task1.domain.Question;

import java.util.List;

public class CheckingServiceImpl implements CheckingService {
    private final IOService ioService;

    public CheckingServiceImpl(IOService ioService) {
        this.ioService = ioService;
    }

    @Override
    public int check(List<Question> questionList) {
        int result = 0;
            for (var question:questionList) {
                int answer = this.ioService.askInt(question.getAsk());
                if (answer == Integer.valueOf(question.getAnswer())) {
                    result++;
                }
                ioService.print("Правильный ответ: " + question.getAnswer() + System.lineSeparator());
            }
        return result;
    }
}
