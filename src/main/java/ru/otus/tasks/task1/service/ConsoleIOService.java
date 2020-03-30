package ru.otus.tasks.task1.service;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.MessageSource;
import org.springframework.stereotype.Service;
import ru.otus.tasks.task1.config.LocaleProps;

import java.io.InputStream;
import java.io.PrintStream;
import java.util.Scanner;

@Service
public class ConsoleIOService implements IOService {
    private final InputStream input;
    private final Scanner in;
    private final PrintStream output;
    private final MessageSource messageSource;
    private LocaleProps localeProps;

    public ConsoleIOService(@Value("#{T(System).in}") InputStream input,
                            @Value("#{T(System).out}") PrintStream output,
                            MessageSource messageSource,
                            LocaleProps localeProps) {
        this.input = input;
        this.output = output;
        this.in = new Scanner(input);
        this.messageSource = messageSource;
        this.localeProps = localeProps;
    }

    @Override
    public String askStr() {
        String answer = in.nextLine();
        return answer;
    }

    @Override
    public int askInt() {
        return Integer.valueOf(askStr());
    }

    @Override
    public void printMessage(String key) {
        output.print(messageSource.getMessage(key, null, localeProps.getCurrentLocale()) + " ");
    }

    @Override
    public void printlnString(String sentence) {
        output.println(sentence + " ");
    }

    @Override
    public void printString(String sentence) {
        output.print(sentence + " ");
    }


    public void close() {
        this.in.close();
    }
}
