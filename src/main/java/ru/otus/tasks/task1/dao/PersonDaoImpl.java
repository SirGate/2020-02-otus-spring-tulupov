package ru.otus.tasks.task1.dao;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.MessageSource;
import org.springframework.stereotype.Repository;
import ru.otus.tasks.task1.domain.Person;
import ru.otus.tasks.task1.service.IOService;

import java.util.Locale;


@Repository
public class PersonDaoImpl implements PersonDao {
    private MessageSource messageSource;
    private  final Locale rus = new Locale("ru", "RU");

    @Autowired
    @Qualifier("messageSourceRu")
    private MessageSource messageSourceRu;

    @Autowired
    @Qualifier("messageSourceEn")
    private MessageSource messageSourceEn;

    final private IOService ioService;

   @Autowired
   public PersonDaoImpl(IOService ioService) {
        this.ioService = ioService;
    }

    @Override
    public Person getNewPerson() {
        int language = ioService.askInt(messageSourceRu.getMessage("question.language",null, rus));
        if (language == 1) messageSource = messageSourceRu;
        else if (language == 2) messageSource = messageSourceEn;
        String familyName = ioService.askStr(messageSource.getMessage("question.surname",null, Locale.getDefault()));
        String name = ioService.askStr(messageSource.getMessage("question.name",null, Locale.getDefault()));
        Person person = new Person(familyName, name);
        if (language == 1) person.setLanguage(1);
        else if (language == 2) person.setLanguage(2);
        return person;
    }

}
