package ru.otus.tasks.task1.service;

import ru.otus.tasks.task1.domain.Question;

import java.io.InputStream;
import java.util.List;

public class CheckingServiceImpl implements CheckingService {
    private InputStream ioService;

    @Override
    public int check(List<Question> questionList, IOService ioService) {
        int result = 0;
            for (var question:questionList) {
                int answer = ioService.askInt(question.getAsk());
                if (answer == Integer.valueOf(question.getAnswer())) {
                    result++;
                }
                ioService.print("Правильный ответ: " + question.getAnswer() + System.lineSeparator());
            }
        return result;
    }

    public void setIoService(InputStream ioService) {
        this.ioService = ioService;
    }
}
