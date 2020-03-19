package ru.otus.tasks.task1.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.MessageSource;
import org.springframework.stereotype.Service;
import ru.otus.tasks.task1.dao.PersonDao;
import ru.otus.tasks.task1.dao.QuestionDao;
import ru.otus.tasks.task1.domain.Person;
import ru.otus.tasks.task1.domain.Question;

import java.io.IOException;
import java.util.List;
import java.util.Locale;

@Service
public class RunnerImpl implements Runner {

    private final PersonDao personDao;
    private final QuestionDao questionDaoEn;
    private final QuestionDao questionDaoRu;
    private final CheckingService checkingService;
    private final IOService consoleIOService;

    private MessageSource messageSource;

    @Autowired
    private int isPassed;

    @Autowired
    @Qualifier("messageSourceRu")
    private MessageSource messageSourceRu;

    @Autowired
    @Qualifier("messageSourceEn")
    private MessageSource messageSourceEn;



    public RunnerImpl(PersonDao personDao, @Qualifier("questionsDaoEn") QuestionDao questionDaoEn,
                      @Qualifier("questionsDaoRu") QuestionDao questionDaoRu,
                       CheckingService checkingService, IOService consoleIOService) {
        this.personDao = personDao;
        this.questionDaoEn = questionDaoEn;
        this.questionDaoRu = questionDaoRu;
        this.checkingService = checkingService;
        this.consoleIOService = consoleIOService;
    }

    public void startTesting() throws IOException {

     Person person = personDao.getNewPerson();
        List<Question> questions = null;
        QuestionDao questionDao = null;
        if (person.getLanguage() == 1) {
            questionDao = questionDaoRu;
            messageSource = messageSourceRu;
        }
        else if (person.getLanguage() == 2) {
            questionDao = questionDaoEn;
            messageSource = messageSourceEn;
        }

     try {
         questions = questionDao.getNewQuestions();
     } catch (IOException e) {
         throw new IOException();
     }
     int result = checkingService.check(questions, person.getLanguage());
     consoleIOService.print(messageSource.getMessage("answer.student",null, Locale.getDefault())
             +
             person.getFamilyName() + " " + person.getName());
     consoleIOService.print(messageSource.getMessage("answer.result1",null, Locale.getDefault())
             +
             result
             +
             " "
             +
             messageSource.getMessage("answer.result2",null, Locale.getDefault())
             +
             System.lineSeparator());
            String passed;
       if (result < isPassed ) {
           passed = messageSource.getMessage("answer.notpassed",null, Locale.getDefault());
       } else {
           passed = messageSource.getMessage("answer.passed",null, Locale.getDefault());
       }
       consoleIOService.print(passed);
       consoleIOService.close();
 }
}
