package ru.otus.tasks.task1.service;

import java.io.IOException;
import java.io.InputStream;
import java.io.PrintStream;

public interface Runner {
    static final InputStream IN = System.in;
    static final PrintStream OUT = System.out;
    void startTesting() throws IOException;
}
