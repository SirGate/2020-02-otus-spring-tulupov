package ru.otus.tasks.task1;

import org.springframework.context.support.ClassPathXmlApplicationContext;
import ru.otus.tasks.task1.service.PersonServiceImpl;
import ru.otus.tasks.task1.service.RunnerImpl;

public class Main {
    public static void main(String[] args) {
        ClassPathXmlApplicationContext context = new ClassPathXmlApplicationContext("/spring-context.xml");
        PersonServiceImpl service = context.getBean(PersonServiceImpl.class);
        RunnerImpl runner = new RunnerImpl(service);
        runner.startTesting();
        context.close();
    }
}
