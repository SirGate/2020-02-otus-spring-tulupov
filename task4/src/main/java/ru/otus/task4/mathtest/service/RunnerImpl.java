package ru.otus.task4.mathtest.service;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import ru.otus.task4.mathtest.config.LocaleProps;
import ru.otus.task4.mathtest.dao.PersonDao;
import ru.otus.task4.mathtest.dao.QuestionDao;
import ru.otus.task4.mathtest.domain.Person;
import ru.otus.task4.mathtest.domain.Question;
import ru.otus.task4.mathtest.exc.QuestionsLoadException;

import java.util.List;

@Service
public class RunnerImpl implements Runner {

    private final PersonDao personDao;
    private final QuestionDao questionDao;
    private final CheckingService checkingService;
    private final IOService consoleIOService;
    private final int sufficientResult;
    private final LocaleProps localeProps;
    private String family;
    private String name;
    private List<Question> questions = null;

    public RunnerImpl(PersonDao personDao, QuestionDao questionDao,
                      CheckingService checkingService, IOService consoleIOService,
                      LocaleProps localeProps,
                      @Value("${sufficientResult}") int sufficientResult
    ) {
        this.personDao = personDao;
        this.questionDao = questionDao;
        this.checkingService = checkingService;
        this.consoleIOService = consoleIOService;
        this.sufficientResult = sufficientResult;
        this.localeProps = localeProps;
    }

    public void startTesting() {
        Person person = personDao.getNewPerson(family, name);
        try {
            questions = questionDao.getNewQuestions();
        } catch (QuestionsLoadException e) {
            e.printStackTrace();
        }
        int result = checkingService.check(questions);
        printResult(person, result);
    }

    private void printResult(Person person, int result) {
        consoleIOService.printMessage("answer.student");
        consoleIOService.printString(person.getFamilyName() + " " + person.getName());
        consoleIOService.printMessage("answer.result1");
        consoleIOService.printString(String.valueOf(result));
        consoleIOService.printMessage("answer.result2");
        consoleIOService.printlnString("" + System.lineSeparator());

        if (result < sufficientResult) {
            consoleIOService.printMessage("answer.notpassed");
            consoleIOService.printlnString("" + System.lineSeparator());
        } else {
            consoleIOService.printMessage("answer.passed");
            consoleIOService.printlnString("" + System.lineSeparator());
        }
    }

    public void setLanguage() {
        consoleIOService.printMessage("question.language");
        consoleIOService.printlnString("");
        Integer counter = 1;
        String[] languages = localeProps.getAvailableLanguages().keySet().
                toArray(new String[localeProps.getAvailableLanguages().size()]);
        for (String speech : languages) {
            consoleIOService.printlnString((counter++) + "." + speech);
        }
        int language = consoleIOService.askInt();
        consoleIOService.printlnString("");
        if (language > 0 && language < languages.length + 1) {
            String locale = languages[language - 1];
            localeProps.setLocale(locale);
        } else {
            consoleIOService.printlnString("Такого языка не существует. Выбран язык по умолчанию.");
            consoleIOService.printlnString("");
        }
    }

    public void login() {
        consoleIOService.printMessage("question.surname");
        this.family = consoleIOService.askStr();
        consoleIOService.printlnString("");
        consoleIOService.printMessage("question.name");
        this.name = consoleIOService.askStr();
        consoleIOService.printlnString("");
    }

    public boolean isLoggedIn() {
        return family != null & name != null;
    }
}
