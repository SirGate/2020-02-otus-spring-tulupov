package ru.otus.library.changelogs;

import com.github.cloudyrock.mongock.ChangeLog;
import com.github.cloudyrock.mongock.ChangeSet;
import com.mongodb.client.MongoDatabase;
import org.springframework.data.mongodb.core.MongoTemplate;

import ru.otus.library.domain.Author;
import ru.otus.library.domain.Book;
import ru.otus.library.domain.Comment;
import ru.otus.library.domain.Genre;

import java.util.List;


@ChangeLog(order = "001")
public class LibraryChangelog {
    private Author Lem;
    private Author Belov;
    private Author Twain;
    private Genre fiction;
    private Genre adventures;
    private Book book;
    private Comment comment1 = new Comment();
    private Comment comment2 = new Comment();
    private Comment comment3 = new Comment();
    private Comment comment4 = new Comment();


    @ChangeSet(order = "000", id = "dropDB", author = "SirGate", runAlways = true)
    public void dropDB(MongoDatabase database) {
        database.drop();
    }

    @ChangeSet(order = "001", id = "addAuthors", author = "SirGate", runAlways = true)
    public void insertAuthors(MongoTemplate template) {
        Lem = template.save(Author.builder().name("Stanislav").surname("Lem").build());
        Belov = template.save(Author.builder().name("Ivan").surname("Belov").build());
        Twain = template.save(Author.builder().name("Mark").surname("Twain").build());
    }

    @ChangeSet(order = "002", id = "addGenres", author = "SirGate", runAlways = true)
    public void insertGenres(MongoTemplate template) {
        fiction = template.save(new Genre("Science fiction"));
        adventures = template.save(new Genre("Adventures"));
    }

    @ChangeSet(order = "003", id = "addBookWithComments", author = "SirGate", runAlways = true)
    public void insertBooks(MongoTemplate template) {
        comment1.setText("It's a good book");
        template.save(comment1);
        comment2.setText("It's a splendid book");
        template.save(comment2);
        comment3.setText("For me it's not an interesting book");
        template.save(comment3);
        comment4.setText("It's a fascinating book");
        template.save(comment4);
        book = Book.builder().title("Solaris").genre(fiction).
                authors(List.of(Lem, Belov)).comments(List.of(comment1, comment2)).build();
        template.save(book);
        Lem.setBooks(List.of(book));
        Belov.setBooks(List.of(book));
        template.save(Lem);
        template.save(Belov);
        book = Book.builder().title("Huckleberry Finn").genre(adventures).
                authors(List.of(Twain)).comments(List.of(comment3, comment4)).build();
        template.save(book);
        Twain.setBooks(List.of(book));
        template.save(Twain);
    }
}
