package ru.otus.library.domain;

public class Genre {
    private final long id;
    private final String description;

    public Genre(long id, String description) {
        this.id = id;
        this.description = description;
    }

    public long getId() {
        return id;
    }

    public String getDescription() {
        return description;
    }

    @Override
    public String toString() {
        return "Genre{" +
                "id=" + id +
                ", description='" + description + '\'' +
                '}';
    }
}
