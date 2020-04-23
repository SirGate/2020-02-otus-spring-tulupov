package ru.otus.task4.mathtest.domain;

public class Person {
    private  String name;
    private  String familyName;

   public String getName() {
        return name;
    }

    public String getFamilyName() {
        return familyName;
    }

    public Person(String familyName, String name) {
        this.familyName = familyName;
        this.name = name;
    }
}
