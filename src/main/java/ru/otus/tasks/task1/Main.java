package ru.otus.tasks.task1;

import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import ru.otus.tasks.task1.service.*;

@Configuration
@ComponentScan
public class Main {
    public static void main(String[] args) {
 AnnotationConfigApplicationContext context = new AnnotationConfigApplicationContext(Main.class);
        Runner runner = context.getBean(RunnerImpl.class);
        runner.startTesting();
    }
}
