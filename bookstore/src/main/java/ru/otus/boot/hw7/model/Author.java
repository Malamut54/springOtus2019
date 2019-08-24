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
public class Author {
    @Id
    @GeneratedValue
    private Long id;
    @NonNull
    @Column(name = "first_name", nullable = false)
    private String firstName;
    @NonNull
    @Column(name = "last_name", nullable = false)
    private String lastName;
}
