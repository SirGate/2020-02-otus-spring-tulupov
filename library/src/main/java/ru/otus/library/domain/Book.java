package ru.otus.library.domain;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Document(collection = "books")
public class Book {
    @Id
    private String id;
    private Genre genre;
    private String title;
    private List<Author> authors = new ArrayList<>();
    private List<Comment> comments = new ArrayList<>();

    public Book(String title, Genre genre, Author... authors) {
        this.title = title;
        this.genre = genre;
        this.authors = Arrays.asList(authors);
    }

    public void addAuthor(Author author) {
        authors.add(author);
    }

    public boolean getAuthor(Author author) {
        return authors.contains(author);
    }

    public void addComment(Comment comment) {
        comments.add(comment);
    }
}
