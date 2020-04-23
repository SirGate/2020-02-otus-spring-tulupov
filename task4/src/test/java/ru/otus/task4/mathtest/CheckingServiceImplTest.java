package ru.otus.task4.mathtest;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.context.MessageSource;
import org.springframework.shell.jline.InteractiveShellApplicationRunner;
import org.springframework.shell.jline.ScriptShellApplicationRunner;
import ru.otus.task4.mathtest.config.LocaleProps;
import ru.otus.task4.mathtest.domain.Person;
import ru.otus.task4.mathtest.domain.Question;
import ru.otus.task4.mathtest.service.CheckingService;
import ru.otus.task4.mathtest.service.IOService;


import java.util.List;
import java.util.Locale;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.when;

@SpringBootTest(properties = {
        InteractiveShellApplicationRunner.SPRING_SHELL_INTERACTIVE_ENABLED + "=false",
        ScriptShellApplicationRunner.SPRING_SHELL_SCRIPT_ENABLED + "=false"
})
public class CheckingServiceImplTest {

    @Autowired
    private MessageSource messageSource;

    @Autowired
    private LocaleProps localeProps;

    @MockBean
    private IOService ioService;

    @Autowired
    private CheckingService checkingService;

    @Test
    void shouldExecuteRussianLocalisation() {
        localeProps.setLocale("Russian");
        String expected = "Тест пройден";
        assertThat(messageSource.getMessage("answer.passed", null,
                Locale.forLanguageTag(localeProps.getCurrentLocale().toString())))
                .isEqualTo(expected);

    }

    @Test
    void shouldExecuteEnglishInternationalization() {
        localeProps.setLocale("English");
        String expected = "Test has been passed";
        assertThat(messageSource.getMessage("answer.passed", null,
                Locale.forLanguageTag(localeProps.getCurrentLocale().toString())))
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
