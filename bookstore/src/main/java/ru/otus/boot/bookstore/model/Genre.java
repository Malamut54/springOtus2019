package ru.otus.boot.bookstore.model;

import lombok.*;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Data
@RequiredArgsConstructor
@Document
@ToString(exclude = "id")
public class Genre {
    @Id
    private String id;
    @NonNull
    private String genreName;
}