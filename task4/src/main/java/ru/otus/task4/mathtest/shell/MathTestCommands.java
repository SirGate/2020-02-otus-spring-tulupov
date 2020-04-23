package ru.otus.task4.mathtest.shell;

import org.springframework.shell.Availability;
import org.springframework.shell.standard.ShellComponent;
import org.springframework.shell.standard.ShellMethod;
import org.springframework.shell.standard.ShellMethodAvailability;
import ru.otus.task4.mathtest.service.Runner;

@ShellComponent

public class MathTestCommands {
    private final Runner runner;

    public MathTestCommands(Runner runner) {
        this.runner = runner;
    }

    @ShellMethod(value = "Input your surname and name", key = "login")
    public void login() {
        runner.login();
    }

    @ShellMethod(value = "Selecting language", key = "language")
    public void language() {
        runner.setLanguage();
    }

    @ShellMethod(value = "Start Math test", key = "start")
    @ShellMethodAvailability(value = "isNameAndFamilyEntered")
    public void start() {
        runner.startTesting();
    }

    private Availability isNameAndFamilyEntered() {
        return runner.isLoggedIn() ? Availability.available() : Availability.
                unavailable("Because you should log in");
    }
}
