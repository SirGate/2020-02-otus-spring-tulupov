package ru.otus.tasks.task1.dao;

import ru.otus.tasks.task1.domain.Person;
import ru.otus.tasks.task1.service.Input;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;

public class PersonDaoGet implements PersonDao {
    @Override
    public Person getNewPerson(Input input) {
        var familyName = input.askStr("Введите пожалуйста вашу фамилию:");
        var name = input.askStr("Введите пожалуйста ваше имя:");
        return new Person(familyName, name);
    }

    @Override
    public int check(Person person, String path, Input input) {
        String question;
      int result = 0;
        try {
            InputStream i = this.getClass().getClassLoader().getResourceAsStream(path);
            BufferedReader fileInput = new BufferedReader(new InputStreamReader(i));
            while ((question = fileInput.readLine()) != null) {
               String[] questions =  question.split(",");
              int answer = input.askInt(questions[0]);
              if (answer == Integer.valueOf(questions[1])) {
                  result++;
              }
            System.out.println("Правильный ответ: " + questions[1] + System.lineSeparator());
            }
            i.close();
              } catch (Exception e) {
                e.printStackTrace();
            }
        return result;
    }
}
