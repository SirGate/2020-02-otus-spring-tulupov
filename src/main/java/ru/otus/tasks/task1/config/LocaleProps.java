package ru.otus.tasks.task1.config;

import lombok.Getter;
import lombok.Setter;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import java.util.Locale;
import java.util.Map;



@Component

public class LocaleProps {

    @Getter
    private Map<String, String> availableLanguages;

    private Map<String, String> questionFileByLanguages;

    @Getter
    @Setter
    private Locale currentLocale;

    public LocaleProps(@Value("#{${available.languages}}") Map<String, String> availableLunguages,
                     @Value("#{${question.files.by.languages}}") Map<String, String> questionFileByLanguages) {
        this.availableLanguages = availableLunguages;
        this.questionFileByLanguages = questionFileByLanguages;
        currentLocale = Locale.forLanguageTag(availableLunguages.get("Russian"));
    }

    public String getQuestionsFile() {
        return questionFileByLanguages.get(currentLocale.toString());
    }
}
