package ru.otus.tasks.task1.dao;

import ru.otus.tasks.task1.domain.Person;
import ru.otus.tasks.task1.service.IOService;

public class PersonDaoImpl implements PersonDao {

   private IOService ioService;

    public PersonDaoImpl(IOService ioService) {
        this.ioService = ioService;
    }

    @Override
    public Person getNewPerson() {
        String familyName = ioService.askStr("Введите пожалуйста вашу фамилию:");
        String name = ioService.askStr("Введите пожалуйста ваше имя:");
        return new Person(familyName, name);
    }
}
