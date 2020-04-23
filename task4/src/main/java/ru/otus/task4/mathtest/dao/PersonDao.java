package ru.otus.task4.mathtest.dao;


import ru.otus.task4.mathtest.domain.Person;

public interface PersonDao {
    Person getNewPerson(String family, String name);
}
