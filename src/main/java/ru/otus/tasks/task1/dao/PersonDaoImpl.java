package ru.otus.tasks.task1.dao;

import ru.otus.tasks.task1.domain.Person;
import ru.otus.tasks.task1.service.ConsoleIOService;

public class PersonDaoImpl implements PersonDao {

   private ConsoleIOService ioService;

    public void setIoService(ConsoleIOService ioService) {
        this.ioService = ioService;
    }

    @Override
    public Person getNewPerson() {
        String familyName = ioService.askStr("Введите пожалуйста вашу фамилию:");
        String name = ioService.askStr("Введите пожалуйста ваше имя:");
        return new Person(familyName, name);
    }
}
