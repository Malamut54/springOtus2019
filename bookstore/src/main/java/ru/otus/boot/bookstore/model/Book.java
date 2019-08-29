package ru.otus.boot.bookstore.model;

import lombok.Data;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Document
@Data
@RequiredArgsConstructor
public class Book {
    @Id
    private String id;
    @NonNull
    private String bookName;
    @NonNull
    private Author author;
    @NonNull
    private Genre genre;
}