package ru.otus.tasks.task1;

import org.springframework.context.support.ClassPathXmlApplicationContext;
import ru.otus.tasks.task1.service.*;

public class Main {
    public static void main(String[] args) {
        ClassPathXmlApplicationContext context = new ClassPathXmlApplicationContext("/spring-context.xml");
        RunnerImpl runner = context.getBean(RunnerImpl.class);
        runner.startTesting();
        context.close();
    }
}
