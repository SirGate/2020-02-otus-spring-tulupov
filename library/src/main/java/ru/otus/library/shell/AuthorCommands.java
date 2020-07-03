package ru.otus.library.shell;

import lombok.AllArgsConstructor;
import org.springframework.shell.standard.ShellComponent;
import org.springframework.shell.standard.ShellMethod;
import ru.otus.library.domain.Author;

import ru.otus.library.repository.AuthorRepository;

@ShellComponent
@AllArgsConstructor
public class AuthorCommands {
    private final AuthorRepository authorRepository;

    @ShellMethod(value = "Create Author", key = "create author")
    public void saveAuthor(String name, String surname) {
        Author author = new Author();
        author.setName(name);
        author.setSurname(surname);
        authorRepository.save(author);
    }

    @ShellMethod(value = "Update Author's Surname and Name", key = "edit author")
    public void editAuthor(String nameOld, String surnameOld, String nameNew, String surnameNew) {
        if (authorRepository.findBySurnameAndName(surnameOld, nameOld).isPresent()) {
            Author author = authorRepository.findBySurnameAndName(surnameOld, nameOld).get();
            author.setName(nameNew);
            author.setSurname(surnameNew);
            authorRepository.save(author);
        }
    }

    @ShellMethod(value = "Delete Author by name and surname", key = "delete author")
    public void deleteAuthor(String name, String surname) {
        if (authorRepository.findBySurnameAndName(surname, name).isPresent()) {
            long id = authorRepository.findBySurnameAndName(surname, name).get().getId();
            authorRepository.deleteById(id);
        }

    }

    @ShellMethod(value = "Show all Authors", key = "getAllA")
    public void getAllA() {
        System.out.println("All count " + authorRepository.count());
        for (Author author : authorRepository.findAll()) {
            System.out.println("Author id: " + author.getId() + " " + "Name: " + author.getName() + " " + author.getSurname());
        }
    }
}
