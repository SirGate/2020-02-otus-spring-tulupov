package ru.otus.library.domain;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Document(collection = "genres")
public class Genre {
    @Id
  //  @GeneratedValue(strategy = GenerationType.IDENTITY)
    private String id;

 //   @Column(name = "description", nullable = false)
    private String description;
}
