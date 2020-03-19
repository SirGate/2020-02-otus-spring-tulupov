package ru.otus.tasks.task1.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.MessageSource;
import org.springframework.stereotype.Service;
import ru.otus.tasks.task1.domain.Question;

import java.util.List;
import java.util.Locale;

@Service
public class CheckingServiceImpl implements CheckingService {
    private final IOService ioService;

    private MessageSource messageSource;

    @Autowired
    @Qualifier("messageSourceRu")
    private MessageSource messageSourceRu;

    @Autowired
    @Qualifier("messageSourceEn")
    private MessageSource messageSourceEn;


    public CheckingServiceImpl(IOService ioService) {
        this.ioService = ioService;
    }

    @Override
    public int check(List<Question> questionList, int language) {
        if (language == 1) {
            messageSource = messageSourceRu;
        }
        else if (language == 2) {
            messageSource = messageSourceEn;
        }
        int result = 0;
            for (var question:questionList) {
                int answer = this.ioService.askInt(question.getAsk());
                if (answer == Integer.valueOf(question.getAnswer())) {
                    result++;
                }
                ioService.print(messageSource.getMessage("answer.right",null, Locale.getDefault())
                        +
                        question.getAnswer() + System.lineSeparator());
            }
        return result;
    }
}
