package ru.otus.boot.hw6.model;


import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;

@Entity
@Getter
@Setter
public class Comment {
    @Id
    @GeneratedValue
    private Long id;
    @Column(name = "comment", nullable = false)
    private String comment;
    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "book_id", nullable = false)
    private Book book;

    public Comment() {
    }

    public Comment(String comment, Book book) {
        this.comment = comment;
        this.book = book;
    }
}
