package ru.otus.task4.mathtest.service;

public interface IOService {
    String askStr();
    int askInt();
    void printMessage(String str);
    void printString(String str);
    void printlnString(String str);
    void close();
}
