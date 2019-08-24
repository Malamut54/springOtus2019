package ru.otus.boot.hw7.model;

import lombok.*;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

@Entity
@Data
@RequiredArgsConstructor
@NoArgsConstructor
public class Genre {
    @Id
    @GeneratedValue
    private Long id;
    @NonNull
    @Column(name = "genre_name", nullable = false)
    private String genreName;
}
