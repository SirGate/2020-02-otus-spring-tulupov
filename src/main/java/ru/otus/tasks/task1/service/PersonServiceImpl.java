package ru.otus.tasks.task1.service;

import ru.otus.tasks.task1.dao.PersonDaoImpl;
import ru.otus.tasks.task1.dao.QuestionDaoImpl;
import ru.otus.tasks.task1.domain.Person;


public class PersonServiceImpl implements PersonService {

    private final PersonDaoImpl daoPerson;
    private final QuestionDaoImpl daoQuestion;
    private final String path;
    private ConsoleIOService ioService;

    public void setIoService(ConsoleIOService ioService) {
        this.ioService = ioService;
    }

    public PersonServiceImpl(PersonDaoImpl daoPerson, QuestionDaoImpl daoQuestion, String path) {
          this.path = path;
          this.daoPerson = daoPerson;
          this.daoQuestion = daoQuestion;
    }

    @Override
    public Person getPerson() {
        daoPerson.setIoService(ioService);
        return daoPerson.getNewPerson();
    }

    @Override
    public int getChecked(Person person, IOService ioService) {

        return daoQuestion.check(person, path, ioService);
    }
}
