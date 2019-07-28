package ru.otus.boot.hw6.model;

import lombok.Data;

import javax.persistence.*;
import java.util.List;

@Entity
@Data
public class Book {
    @Id
    @GeneratedValue
    private Long id;
    @Column(name = "book_name")
    private String bookName;
    @OneToOne(fetch = FetchType.EAGER,cascade=CascadeType.ALL)
    @JoinColumn(name = "author_id", nullable = false)
    private Author author;
    @OneToOne(fetch = FetchType.EAGER,cascade=CascadeType.ALL)
    @JoinColumn(name = "genre_id", nullable = false)
    private Genre genre;


    public Book() {
    }

    public Book(String bookName, Author author, Genre genre) {
        this.bookName = bookName;
        this.author = author;
        this.genre = genre;
    }
}
