package ru.otus.tasks.task1.config;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.MessageSource;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.support.ReloadableResourceBundleMessageSource;
import ru.otus.tasks.task1.dao.QuestionDao;
import ru.otus.tasks.task1.dao.QuestionDaoImpl;

@Configuration
public class DaoConfig {

    @Bean
    public int isPassed(@Value("3") String sufficientResult){
        return Integer.valueOf(sufficientResult);
    }



    @Bean
    public MessageSource messageSourceRu() {
        ReloadableResourceBundleMessageSource ms = new ReloadableResourceBundleMessageSource();
        ms.setBasename("questions_ru_RU");
        ms.setDefaultEncoding("UTF-8");
        return ms;
    }

    @Bean
    public MessageSource messageSourceEn() {
        ReloadableResourceBundleMessageSource ms = new ReloadableResourceBundleMessageSource();
        ms.setBasename("questions_en");
        ms.setDefaultEncoding("UTF-8");
        return ms;
    }

    @Bean
    QuestionDao questionsDaoEn(@Value("${classpath:questions.csv}") String path) {
        return new QuestionDaoImpl(path);
 }

    @Bean
    QuestionDao questionsDaoRu(@Value("${classpath:questions_ru.csv}") String path) {
        return new QuestionDaoImpl(path);
    }
}
