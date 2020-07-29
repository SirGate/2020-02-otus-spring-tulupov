package ru.otus.library.domain;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;
import java.util.ArrayList;
import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Document(collection = "books")
public class Book {
    @Id
 //   @GeneratedValue(strategy = GenerationType.IDENTITY)
    private String id;

 //   @ManyToMany(fetch = FetchType.EAGER)
//    @JoinTable(name = "books_authors", joinColumns = @JoinColumn(name = "book_id", referencedColumnName = "id"),
  //          inverseJoinColumns = @JoinColumn(name = "author_id", referencedColumnName = "id"))
    private List<Author> authors = new ArrayList<>();


 //   @ManyToOne(fetch = FetchType.EAGER)
//    @JoinColumn(name = "genre_id")
    private Genre genre;

  //  @Column(name = "title", nullable = false)
    private String title;

 //   @OneToMany(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
 //   @JoinColumn(name = "book_id")
    private List<Comment> comments = new ArrayList<>();

    public void addComment(Comment comment) {
        comments.add(comment);
        comment.setBook(this);
    }

    public void removeComment(Comment comment) {
        this.comments.remove(comment);
        comment.setBook(null);
    }

    public void addAuthor(Author author) {
        authors.add(author);
    }

    public void removeAuthor(Author author) {
        authors.remove(author);
        this.setAuthors(authors);
    }
}
