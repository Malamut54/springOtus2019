package ru.otus.boot.bookstore.model;

import lombok.Data;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;
import lombok.ToString;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Data
@Document
@ToString(exclude = "id")
@RequiredArgsConstructor
public class Author {
    @Id
    private String id;
    @NonNull
    private String firstName;
    @NonNull
    private String lastName;
}
