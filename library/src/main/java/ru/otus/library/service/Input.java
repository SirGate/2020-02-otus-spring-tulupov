package ru.otus.library.service;

import org.springframework.stereotype.Service;

public interface Input {
    int askInt(String question, int max);
}
