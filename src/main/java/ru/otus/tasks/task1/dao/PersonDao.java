package ru.otus.tasks.task1.dao;

import ru.otus.tasks.task1.domain.Person;
import ru.otus.tasks.task1.service.Input;

public interface PersonDao {
    Person getNewPerson(Input input);
    int check(Person person, String path, Input input);
}
