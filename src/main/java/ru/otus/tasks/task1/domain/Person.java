package ru.otus.tasks.task1.domain;

public class Person {
    private String name;
    private String familyName;
    private int language;

    public int getLanguage() {
        return language;
    }

    public void setLanguage(int language) {
        this.language = language;
    }

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
