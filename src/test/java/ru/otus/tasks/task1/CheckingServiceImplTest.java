package ru.otus.tasks.task1;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import static org.assertj.core.api.Assertions.assertThat;

import ru.otus.tasks.task1.config.AppConfig;
import ru.otus.tasks.task1.config.LocaleProps;
import ru.otus.tasks.task1.domain.Person;
import ru.otus.tasks.task1.domain.Question;
import ru.otus.tasks.task1.service.CheckingService;
import ru.otus.tasks.task1.service.IOService;

import java.util.List;

import static org.mockito.Mockito.*;

@ExtendWith(SpringExtension.class)
@ContextConfiguration(classes = {Main.class, AppConfig.class, TestContextConfig.class})
public class CheckingServiceImplTest {

    @Autowired
    private MessageSource messageSource;

    @Autowired
    private LocaleProps localeProps;

    @Autowired
    private IOService ioService;

    @Autowired
    private CheckingService checkingService;

    @Test
    void shouldExecuteRussianLocalisation() {
        localeProps.setCurrentLocale("Russian");
        String expected = "Тест пройден";
        assertThat(messageSource.getMessage("answer.passed", null, localeProps.getCurrentLocale())).
                isEqualTo(expected);
    }

    @Test
    void shouldExecuteEnglishInternationalization() {
        localeProps.setCurrentLocale("English");
        String expected = "Test has been passed";
        assertThat(messageSource.getMessage("answer.passed", null, localeProps.getCurrentLocale()))
                .isEqualTo(expected);
    }

    @Test
    void whenCheckPerson() {
        Person person = new Person("Petrov", "Ivan");
        List<Question> questions = List.of(
                new Question("", "11"),
                new Question("", "21"),
                new Question("", "31"),
                new Question("", "34"),
                new Question("", "40")
        );
        when(ioService.askInt()).thenReturn(11).thenReturn(21).
                thenReturn(31).thenReturn(34).thenReturn(40);
        int result = checkingService.check(questions);
        assertThat(result).isEqualTo(5);
    }
}
