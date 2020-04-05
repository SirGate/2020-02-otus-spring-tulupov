package otus.service;

import otus.domain.Question;

import java.util.List;

public interface CheckingService {
    int check(List<Question> questions);
}
