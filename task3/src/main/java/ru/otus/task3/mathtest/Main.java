package ru.otus.task3.mathtest;

import otus.service.Runner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ApplicationContext;
import ru.otus.task3.mathtest.service.RunnerImpl;



@SpringBootApplication
public class Main {
    public static void main(String[] args) {
        ApplicationContext context = SpringApplication.run(Main.class, args);
        Runner runner = context.getBean(RunnerImpl.class);
        runner.startTesting();
    }
}
