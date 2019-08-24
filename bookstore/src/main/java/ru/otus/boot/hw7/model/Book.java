package ru.otus.boot.hw7.model;

import lombok.*;

import javax.persistence.*;

@Entity
@Data
@RequiredArgsConstructor
@NoArgsConstructor
public class Book {
    @Id
    @GeneratedValue
    private Long id;
    @NonNull
    @Column(name = "book_name")
    private String bookName;
    @NonNull
    @ManyToOne(cascade = {CascadeType.ALL})
    @JoinColumn(name = "author_id", nullable = false)
    private Author author;
    @NonNull
    @ManyToOne(cascade = {CascadeType.ALL})
    @JoinColumn(name = "genre_id", nullable = false)
    private Genre genre;

}
