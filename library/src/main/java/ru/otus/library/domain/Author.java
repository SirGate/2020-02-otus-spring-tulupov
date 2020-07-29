package ru.otus.library.domain;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Document(collection = "authors")
public class Author {
    @Id
  //  @GeneratedValue(strategy = GenerationType.IDENTITY)
    private String id;

 //   @Column(name = "name", nullable = false)
    private String name;

   // @Column(name = "surname", nullable = false)
    private String surname;
}
