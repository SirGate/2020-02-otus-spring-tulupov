package ru.otus.tasks.task1.service;

import java.util.Scanner;

public class ConsoleInput implements Input {

    private Scanner scanner = new Scanner(System.in);

    @Override
    public String askStr(String question) {
        System.out.println(question);
        String answer = scanner.nextLine();
        scanner.close();
        return answer;
    }

    @Override
    public int askInt(String question) {
        return Integer.valueOf(askStr(question));
    }

}
