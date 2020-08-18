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
    private Genre fiction;
    private Book book;
    private Comment comment1 = new Comment();
    private Comment comment2 = new Comment();


    @ChangeSet(order = "000", id = "dropDB", author = "SirGate", runAlways = true)
    public void dropDB(MongoDatabase database) {
        database.drop();
    }

    @ChangeSet(order = "001", id = "addAuthors", author = "SirGate", runAlways = true)
    public void insertAuthors(MongoTemplate template) {
        Lem = template.save(new Author("Stanislav", "Lem"));
        template.save(new Author("Mark", "Twain"));
    }

    @ChangeSet(order = "002", id = "addGenres", author = "SirGate", runAlways = true)
    public void insertGenres(MongoTemplate template) {
        fiction = template.save(new Genre("Science fiction"));
        template.save(new Genre("Adventures"));
    }

    @ChangeSet(order = "003", id = "addBookWithComments", author = "SirGate", runAlways = true)
    public void insertBooks(MongoTemplate template) {
        comment1.setText("It's a good book");
        template.save(comment1);
        comment2.setText("It's a splendid book");
        template.save(comment2);
        book = Book.builder().title("Solaris").genre(fiction).
                authors(List.of(Lem)).comments(List.of(comment1, comment2)).build();
        template.save(book);
        Lem.addBook(book);
        template.save(Lem);
    }
}
