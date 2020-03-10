package ru.otus.tasks.task1.service;

import ru.otus.tasks.task1.domain.Person;

public class RunnerImpl implements Runner {
    private final PersonServiceImpl service;

    public RunnerImpl(PersonServiceImpl service) {
        this.service = service;
    }

 public void startTesting() {
     ConsoleIOService input = new ConsoleIOService(System.in, System.out);
     service.setIoService(input);
     Person person = service.getPerson();
     int result = service.getChecked(person, input);
     input.print("Student: " + person.getFamilyName() + " " + person.getName());
     input.print("Ответил правильно на " + result + " из 5 вопросов" + System.lineSeparator());
     input.close();
 }
}
