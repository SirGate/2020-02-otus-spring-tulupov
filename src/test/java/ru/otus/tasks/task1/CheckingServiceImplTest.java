package ru.otus.tasks.task1;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.MessageSource;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import static org.assertj.core.api.Assertions.assertThat;

import ru.otus.tasks.task1.config.DaoConfig;
import ru.otus.tasks.task1.domain.Person;
import ru.otus.tasks.task1.domain.Question;
import ru.otus.tasks.task1.service.CheckingService;
import ru.otus.tasks.task1.service.IOService;

import java.util.List;
import java.util.Locale;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;


@ExtendWith(SpringExtension.class)
@ContextConfiguration(classes = {Main.class, DaoConfig.class, TestContextConfig.class})
public class CheckingServiceImplTest {

  @Autowired
  @Qualifier("messageSourceRu")
  private MessageSource messageSourceRu;

   @Autowired
   @Qualifier("messageSourceEn")
   private MessageSource messageSourceEn;

   @Autowired
   private IOService ioService;

   @Autowired
   private CheckingService checkingService;

    @Test
   void shouldExecuteRussianLocalisation() {
        MessageSource messageSource = messageSourceRu;
        String expected = "Тест пройден";
  assertThat(messageSource.getMessage("answer.passed",null, Locale.getDefault())).isEqualTo(expected);
    }

    @Test
    void shouldExecuteEnglishInternationalization() {
        MessageSource messageSource = messageSourceEn;
        String expected = "Test has been passed";
        assertThat(messageSource.getMessage("answer.passed",null, Locale.getDefault())).isEqualTo(expected);
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
       when(ioService.askInt(any())).thenReturn(11).thenReturn(21).
        thenReturn(31).thenReturn(34).thenReturn(40);
        int result = checkingService.check(questions, 1);
       assertThat(result).isEqualTo(5);
    }
}
