package ru.otus.library.domain;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Document(collection = "comments")
public class Comment {
    @Id
 //   @GeneratedValue(strategy = GenerationType.IDENTITY)
    private String id;

   // @ManyToOne
    private Book book;

  //  @Column(name = "text", nullable = false)
    private String text;
}

