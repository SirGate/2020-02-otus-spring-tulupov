package ru.otus.tasks.task1;

import org.junit.Test;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import ru.otus.tasks.task1.domain.Person;
import ru.otus.tasks.task1.domain.Question;
import ru.otus.tasks.task1.service.CheckingServiceImpl;
import ru.otus.tasks.task1.service.IOService;
import ru.otus.tasks.task1.service.RunnerImpl;

import java.util.List;

import static org.hamcrest.core.Is.is;
import static org.junit.Assert.assertThat;

public class CheckingServiceImplTest {

    @Test
    public void whenCheckPerson() {
        ClassPathXmlApplicationContext context = new ClassPathXmlApplicationContext("/spring-context.xml");
        StubIOService input = new StubIOService(new String[] {"11", "21", "31", "34", "40"});
        List<Question> adapter = List.of(
                new Question("", "11"),
                new Question("", "21"),
                new Question("", "31"),
                new Question("", "34"),
                new Question("", "40")
        );
        Person person = new Person("Petrov", "Ivan");
        CheckingServiceImpl checkingService = context.getBean(CheckingServiceImpl.class);
        int check = checkingService.check(adapter, input);
        String result = person.getFamilyName() + " "  + person.getName() + " " + check;
        assertThat(result, is("Petrov Ivan 5"));
        context.close();
    }

    public class StubIOService implements IOService {
        private String[] answers;
        private int position = 0;

        public StubIOService(String[] answers) {
            this.answers = answers;
        }

        @Override
        public String askStr(String question) {
            return answers[position++];
        }

        @Override
        public int askInt(String question) {
            return Integer.valueOf(askStr(question));
        }

        @Override public void print(String message) {
        }

        @Override
        public void close() {

        }
    }
}
