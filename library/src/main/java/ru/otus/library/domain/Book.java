package ru.otus.library.domain;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.DBRef;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.ArrayList;
import java.util.List;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Document(collection = "books")
public class Book {
    @Id
    private String id;

    @DBRef(lazy = true)
    private Genre genre;
    private String title;

    @DBRef(lazy = true)
    private List<Author> authors = new ArrayList<>();

    private List<Comment> comments = new ArrayList<>();

    public void addAuthor(Author author) {
        authors.add(author);
    }

    public void deleteAuthor(Author author) {
        authors.remove(author);
    }

    public boolean getAuthor(Author author) {
        return authors.contains(author);
    }

    public void addComment(Comment comment) {
        comments.add(comment);
    }

    public String printAuthors() {
        String allAuthors = "";
        int count = 0;

        for (var str : authors) {
            if (str != null) {
                allAuthors += str.getName() + " " + str.getSurname();
                count++;
                if (count == authors.size()) {
                    allAuthors += ".";
                } else {
                    allAuthors += ", ";
                }
            }
        }
        return allAuthors;
    }
}
