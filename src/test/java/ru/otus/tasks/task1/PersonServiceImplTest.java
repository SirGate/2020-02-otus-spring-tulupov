package ru.otus.tasks.task1;

import org.junit.Test;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import ru.otus.tasks.task1.domain.Person;
import ru.otus.tasks.task1.service.IOService;
import ru.otus.tasks.task1.service.PersonServiceImpl;

import static org.hamcrest.core.Is.is;
import static org.junit.Assert.assertThat;

public class PersonServiceImplTest {

    @Test
    public void whenCheckPerson() {
        ClassPathXmlApplicationContext context = new ClassPathXmlApplicationContext("/spring-context.xml");
        PersonServiceImpl service = context.getBean(PersonServiceImpl.class);
        StubIOService input = new StubIOService(new String[] {"11", "21", "31", "34", "40"});
        Person person = new Person("Petrov", "Ivan");
        int check = service.getChecked(person, input);
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
    }

}
