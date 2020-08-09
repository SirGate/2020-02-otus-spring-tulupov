package ru.otus.library.service;

import org.springframework.stereotype.Service;

import java.util.Scanner;

@Service
public class ConsoleInput implements Input {
    private Scanner scanner = new Scanner(System.in);

    @Override
    public int askInt(String question, int max) {
        boolean invalid = true;
        int select = -1;
        do {
            try {
                System.out.println("");
                System.out.println(question);
                String entered = scanner.nextLine();
                if (entered.equals("c")) {
                    select = -1;
                    break;
                }
                ;
                select = Integer.valueOf(entered);
                if (select < 1 || select > max) {
                    throw new IllegalStateException();
                }
                invalid = false;
            } catch (IllegalStateException ise) {
                System.out.println("The given number is wrong, please try again.");
            } catch (NumberFormatException nfe) {
                System.out.println("The entered data is incorrect, please try again.");
            }
        } while (invalid);
        return select;
    }
}
