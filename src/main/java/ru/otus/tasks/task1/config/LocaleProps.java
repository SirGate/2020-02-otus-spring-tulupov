package ru.otus.tasks.task1.config;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import java.util.Locale;
import java.util.Map;

@Component
public class LocaleProps {

    private Map<String, String> availableLanguages;
    private Map<String, String> questionFileByLanguages;
    private Locale currentLocale;

    public LocaleProps(@Value("#{${available.languages}}") Map<String, String> availableLanguages,
                       @Value("#{${question.files.by.languages}}") Map<String, String> questionFileByLanguages,
                       @Value("${default.locale}") String locale) {
        this.availableLanguages = availableLanguages;
        this.questionFileByLanguages = questionFileByLanguages;
        currentLocale = availableLanguages.keySet().stream().filter(locale::equals).
                findFirst().map(x -> Locale.forLanguageTag(availableLanguages.get(x)))
                .orElseThrow(() -> new RuntimeException("Default language doesn't available"));
    }

    public Locale getCurrentLocale() {
        return currentLocale;
    }

    public void setCurrentLocale(String locale) {
        currentLocale = availableLanguages.keySet().stream().filter(locale::equals).
                findFirst().map(x -> Locale.forLanguageTag(availableLanguages.get(x)))
                .orElseThrow(() -> new RuntimeException("Language doesn't available"));
    }

    public Map<String, String> getAvailableLanguages() {
        return availableLanguages;
    }

    public String getQuestionsFile() {
        return questionFileByLanguages.get(currentLocale.toString());
    }
}
