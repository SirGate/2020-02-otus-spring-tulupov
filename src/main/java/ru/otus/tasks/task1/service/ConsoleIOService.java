package ru.otus.tasks.task1.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;
import org.springframework.stereotype.Service;

import java.io.InputStream;
import java.io.PrintStream;
import java.util.Scanner;

@Configuration
@Service
public class ConsoleIOService implements IOService {

    private Scanner scanner;
    private InputStream input;
    private PrintStream output;

    @Autowired
    public void setInput(@Value("#{T(System).in}") InputStream input) {
        scanner = new Scanner(input);
        this.input = input;
    }
     @Autowired
     public void setOutput(@Value("#{T(System).out}") PrintStream output) {
        this.output = output;
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
