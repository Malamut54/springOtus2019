package ru.otus.boot.hw6.model;

import lombok.Data;

import javax.persistence.*;

@Entity
@Data
public class Comment {
    @Id
    @GeneratedValue
    private Long id;
    @Column(name = "comment", nullable = false)
    private String comment;
    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "book_id", nullable = false)
    private Book book;

    public Comment(String comment) {
        this.comment = comment;
    }

    public Comment(String comment, Book book) {
        this.comment = comment;
        this.book = book;
    }
}
