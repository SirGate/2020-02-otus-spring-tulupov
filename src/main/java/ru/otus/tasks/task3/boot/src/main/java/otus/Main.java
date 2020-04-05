package otus;

import otus.service.Runner;
import otus.service.RunnerImpl;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ApplicationContext;

@SpringBootApplication
public class Main {
    public static void main(String[] args) {
        ApplicationContext context = SpringApplication.run(Main.class, args);
        Runner runner = context.getBean(RunnerImpl.class);
        runner.startTesting();
    }
}
