package ru.otus.tasks.task1;


import org.springframework.context.support.ClassPathXmlApplicationContext;
import ru.otus.tasks.task1.domain.Person;
import ru.otus.tasks.task1.service.ConsoleInput;
import ru.otus.tasks.task1.service.PersonService;

public class Main {
    public static void main(String[] args) {
        ClassPathXmlApplicationContext context = new ClassPathXmlApplicationContext("/spring-context.xml");
        PersonService service = context.getBean(PersonService.class);
        ConsoleInput input = new ConsoleInput();
        Person person = service.getPerson(input);
        int result = service.getChecked(person, input);
        System.out.println("Student: " + person.getFamilyName() + " " + person.getName());
        System.out.println("Ответил правильно на " + result + " из 5 вопросов" + System.lineSeparator());
        context.close();
    }
}
