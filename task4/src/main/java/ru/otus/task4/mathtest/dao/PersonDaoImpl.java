package ru.otus.task4.mathtest.dao;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import ru.otus.task4.mathtest.domain.Person;
import ru.otus.task4.mathtest.service.IOService;

@Repository
public class PersonDaoImpl implements PersonDao {
       final private IOService ioService;

    @Autowired
    public PersonDaoImpl(IOService ioService) {
        this.ioService = ioService;
    }

    @Override
    public Person getNewPerson(String family, String name) {
        Person person = new Person(family, name);
        ioService.printlnString("");
        return person;
    }
}
