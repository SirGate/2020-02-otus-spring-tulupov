package ru.otus.library;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.data.mongodb.repository.config.EnableMongoRepositories;
import org.springframework.context.ApplicationContext;
import ru.otus.library.service.Runner;
import ru.otus.library.service.RunnerImpl;

@EnableMongoRepositories
@SpringBootApplication
@EnableConfigurationProperties
public class LibraryApplication {

    public static void main(String[] args) {
        ApplicationContext context = SpringApplication.run(LibraryApplication.class, args);
        Runner runner = context.getBean(RunnerImpl.class);
    }
}