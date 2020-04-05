package otus.config;

import org.springframework.beans.factory.annotation.Value;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

import java.util.*;

@Component
@ConfigurationProperties(prefix = "app")
public class LocaleProps {

    private Map<String, String> availableLanguages = new HashMap<String, String>();
    private Map<String, String> questionFileByLanguages = new HashMap<String, String>();
    @Value("${defaultLocale}")
    private Locale currentLocale;

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
