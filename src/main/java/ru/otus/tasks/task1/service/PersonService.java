package ru.otus.tasks.task1.service;

import ru.otus.tasks.task1.domain.Person;

public interface   PersonService {
    Person getPerson();
    int getChecked(Person person, IOService ioService);
}
