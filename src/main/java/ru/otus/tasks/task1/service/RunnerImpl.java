package ru.otus.tasks.task1.service;

import ru.otus.tasks.task1.dao.PersonDao;
import ru.otus.tasks.task1.dao.QuestionDao;
import ru.otus.tasks.task1.domain.Person;
import ru.otus.tasks.task1.domain.Question;

import java.util.List;

public class RunnerImpl implements Runner {
    private final PersonDao personDao;
    private final QuestionDao questionDao;
    private final CheckingService checkingService;
    private final IOService consoleIOService;

    public RunnerImpl(PersonDao personDao, QuestionDao questionDao,
                      CheckingService checkingService, IOService consoleIOService) {
        this.personDao = personDao;
        this.questionDao = questionDao;
        this.checkingService = checkingService;
        this.consoleIOService = consoleIOService;
    }

 public void startTesting() {
     Person person = personDao.getNewPerson();
     List<Question> questions = questionDao.getNewQuestions();
     int result = checkingService.check(questions, consoleIOService);
     consoleIOService.print("Student: " + person.getFamilyName() + " " + person.getName());
     consoleIOService.print("Ответил правильно на " + result + " из 5 вопросов" + System.lineSeparator());
     consoleIOService.close();
 }
}
