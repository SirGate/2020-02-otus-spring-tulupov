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
@Document(collection = "authors")
public class Author {
    @Id
    private String id;
    private String name;
    private String surname;

    @DBRef
    private List<Book> books = new ArrayList<>();

    public Author(String name, String surname) {
        this.name = name;
        this.surname = surname;
    }

    public void addBook(Book book) {
        books.add(book);
    }

    public void deleteBook(Book book) {
        books.remove(book);
    }

    public String printBooks() {
        String allBooks = "";
        int count = 0;
        for (var str : books) {
            if (str != null) {
                allBooks += str.getTitle();
                count++;
                if (count == books.size()) {
                    allBooks += ".";
                } else {
                    allBooks += ", ";
                }
            }
        }
        return allBooks;
    }
}
