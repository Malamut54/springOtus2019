package ru.otus.boot.hw6.model;

import lombok.Data;

import javax.persistence.*;

@Entity
@Data
public class Genre {
    @Id
    @GeneratedValue
    private Long id;
    @Column(name = "genre_name", nullable = false)
    private String genreName;

    public Genre() {
    }

    public Genre(String genreName) {
        this.genreName = genreName;
    }
}
