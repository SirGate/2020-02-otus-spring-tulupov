package ru.otus.tasks.task1.service;

import ru.otus.tasks.task1.dao.PersonDao;
import ru.otus.tasks.task1.domain.Person;

public class PersonServiceImpl implements PersonService {
    private final PersonDao dao;
    private final String path;

    public PersonServiceImpl(PersonDao dao, String path) {
          this.path = path;
          this.dao = dao;
    }

    @Override
    public Person getPerson(Input input) {
        return dao.getNewPerson(input);
    }

    @Override
    public int getChecked(Person person, Input input) {
        return dao.check(person, path, input);
    }
}
