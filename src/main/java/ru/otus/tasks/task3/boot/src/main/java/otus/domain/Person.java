package otus.domain;

public class Person {
    private final String name;
    private final String familyName;

   public String getName() {
        return name;
    }

    public String getFamilyName() {
        return familyName;
    }

    public Person(String familyName, String name) {
        this.familyName = familyName;
        this.name = name;
    }
}
