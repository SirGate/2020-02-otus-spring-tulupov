package ru.otus.tasks.task1.service;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.PropertySource;
import org.springframework.stereotype.Service;
import ru.otus.tasks.task1.config.LocaleProps;
import ru.otus.tasks.task1.dao.PersonDao;
import ru.otus.tasks.task1.dao.QuestionDao;
import ru.otus.tasks.task1.domain.Person;
import ru.otus.tasks.task1.domain.Question;
import ru.otus.tasks.task1.exc.QuestionsLoadException;

import java.util.List;

@Service
@PropertySource("classpath:application.properties")
public class RunnerImpl implements Runner {

    private final PersonDao personDao;
    private final QuestionDao questionDao;
    private final CheckingService checkingService;
    private final IOService consoleIOService;
    private final int sufficientResult;
    private final LocaleProps localeProps;

    public RunnerImpl(PersonDao personDao, QuestionDao questionDao,
                      CheckingService checkingService, IOService consoleIOService,
                      LocaleProps localeProps,
                      @Value("${sufficient.result}") int sufficientResult) {
        this.personDao = personDao;
        this.questionDao = questionDao;
        this.checkingService = checkingService;
        this.consoleIOService = consoleIOService;
        this.sufficientResult = sufficientResult;
        this.localeProps = localeProps;
    }

    public void startTesting() {
        consoleIOService.printMessage("question.language");
        final int language = consoleIOService.askInt();
        consoleIOService.printString("" + System.lineSeparator());
        String locale = "Russian";
        if (language == 2) {
            locale = "English";
        }
        localeProps.setCurrentLocale(locale);
        Person person = personDao.getNewPerson();
        List<Question> questions = null;
        try {
            questions = questionDao.getNewQuestions();
        } catch (QuestionsLoadException e) {
            e.printStackTrace();
        }
        int result = checkingService.check(questions);
        consoleIOService.printString("" + System.lineSeparator());
        consoleIOService.printMessage("answer.student");
        consoleIOService.printString(person.getFamilyName() + " " + person.getName());
        consoleIOService.printMessage("answer.result1");
        consoleIOService.printString(String.valueOf(result));
        consoleIOService.printMessage("answer.result2");
        consoleIOService.printString(" " + System.lineSeparator());

        if (result < sufficientResult) {
            consoleIOService.printString(" " + System.lineSeparator());
            consoleIOService.printMessage("answer.notpassed");
        } else {
            consoleIOService.printString(" " + System.lineSeparator());
            consoleIOService.printMessage("answer.passed");
        }
        consoleIOService.close();
    }
}
