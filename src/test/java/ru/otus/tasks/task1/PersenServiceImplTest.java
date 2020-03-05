package ru.otus.tasks.task1;

import org.junit.Test;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import ru.otus.tasks.task1.domain.Person;
import ru.otus.tasks.task1.service.PersonService;
import ru.otus.tasks.task1.service.StubInput;

import static org.hamcrest.core.Is.is;
import static org.junit.Assert.assertThat;

public class PersenServiceImplTest {

  ClassPathXmlApplicationContext context = new ClassPathXmlApplicationContext("/spring-context.xml");
          PersonService service = context.getBean(PersonService.class);
    @Test
    public void whenCreatePerson() {
          StubInput input = new StubInput(new String[] {"Petrov", "Ivan"});
          Person person = service.getPerson(input);
          assertThat(person.getFamilyName(), is("Petrov"));
          assertThat(person.getName(), is("Ivan"));
          context.close();
      }
    @Test
    public void whenCheckPerson() {
        StubInput input = new StubInput(new String[] {"Petrov", "Ivan", "11", "21", "31", "34", "40"});
        Person person = service.getPerson(input);
        int check = service.getChecked(person, input);
        String result = person.getFamilyName() + " "  + person.getName() + " " + check;
        assertThat(result, is("Petrov Ivan 5"));
        context.close();
    }
}
