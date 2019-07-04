package ru.otus.boot.hw5.model;

import lombok.Data;

@Data
public class Book {
    private final Author author;
    private final Genre genre;
    private final String bookName;
}
