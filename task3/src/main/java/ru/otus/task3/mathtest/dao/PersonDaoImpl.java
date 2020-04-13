package ru.otus.task3.mathtest.dao;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import ru.otus.task3.mathtest.domain.Person;
import ru.otus.task3.mathtest.service.IOService;

@Repository
public class PersonDaoImpl implements PersonDao {

    final private IOService ioService;

    @Autowired
    public PersonDaoImpl(IOService ioService) {
        this.ioService = ioService;
    }

    @Override
    public Person getNewPerson() {
        ioService.printMessage("question.surname");
        final String familyName = ioService.askStr();
        ioService.printlnString("");
        ioService.printMessage("question.name");
        final String name = ioService.askStr();
        Person person = new Person(familyName, name);
        ioService.printlnString("");
        return person;
    }
}
