package ru.otus.tasks.task1;

import org.springframework.context.support.ClassPathXmlApplicationContext;
import ru.otus.tasks.task1.service.*;

import java.io.IOException;

public class Main {
    public static void main(String[] args) {
        ClassPathXmlApplicationContext context = new ClassPathXmlApplicationContext("/spring-context.xml");
        Runner runner = context.getBean(RunnerImpl.class);
        try {
            runner.startTesting();
        } catch (IOException e) {
            e.printStackTrace();
        }
        context.close();
    }
}
