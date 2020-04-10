package ru.otus.task3.mathtest.config;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

import java.util.*;

@Component
@ConfigurationProperties(prefix = "app")
public class LocaleProps {

    private Map<String, String> availableLanguages = new HashMap<>();
    private Map<String, String> questionFileByLanguages = new HashMap<>();
    private Locale currentLocale;

    public void setCurrentLocale(Locale currentLocale) {
        this.currentLocale = currentLocale;
    }

    public Locale getCurrentLocale() {
        return currentLocale;
    }

    public void setLocale(String locale) {
        currentLocale = availableLanguages.keySet().stream().filter(locale::equals).
                findFirst().map(x -> Locale.forLanguageTag(availableLanguages.get(x)))
                .orElseThrow(() -> new RuntimeException("Language doesn't available"));
    }

    public Map<String, String> getAvailableLanguages() {
        return availableLanguages;
    }

    public void setAvailableLanguages(HashMap<String, String> availableLanguages) {
        this.availableLanguages = availableLanguages;
    }

    public Map<String, String> getQuestionFileByLanguages() {
        return questionFileByLanguages;
    }

    public void setQuestionFileByLanguages(Map<String, String> questionFileByLanguages) {
        this.questionFileByLanguages = questionFileByLanguages;
    }

    public String getQuestionsFile() {
        return questionFileByLanguages.get(currentLocale.toString());
    }
}
