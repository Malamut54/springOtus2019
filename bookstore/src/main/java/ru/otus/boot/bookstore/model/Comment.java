package ru.otus.boot.bookstore.model;

import lombok.Data;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;
import lombok.ToString;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Document
@Data
@RequiredArgsConstructor
@ToString
public class Comment {
    @Id
    @ToString.Exclude
    private String id;
    @NonNull
    private String comment;
    @NonNull
    @ToString.Exclude
    private Book book;
}