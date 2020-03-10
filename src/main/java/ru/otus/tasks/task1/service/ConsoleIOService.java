package ru.otus.tasks.task1.service;

import java.io.InputStream;
import java.io.PrintStream;
import java.util.Scanner;


public class ConsoleIOService implements IOService {

    private Scanner scanner;
    private InputStream input;
    private PrintStream output;

    public ConsoleIOService(InputStream input, PrintStream output) {
        this.input = input;
        this.output = output;
        scanner = new Scanner(input);
    }

    @Override
    public String askStr(String question) {
        output.println(question);
        String answer = scanner.nextLine();
        return answer;
    }

    @Override
    public int askInt(String question) {
        return Integer.valueOf(askStr(question));
    }

    @Override
    public void print(String message) {
        output.println(message);
    }

    public void close() {
        this.scanner.close();
    }
}
