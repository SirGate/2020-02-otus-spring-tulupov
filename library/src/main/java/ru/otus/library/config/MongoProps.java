package ru.otus.library.config;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

@Data
@Component
@ConfigurationProperties("spring.data.mongodb")
public class MongoProps {
    static final String CHANGELOGS_PACKAGE = "ru.otus.library.changelogs";
    private int port;
    private String database;
    private String uri;
}
