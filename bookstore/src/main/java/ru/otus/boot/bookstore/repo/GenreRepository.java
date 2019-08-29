package ru.otus.boot.bookstore.repo;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;
import ru.otus.boot.bookstore.model.Genre;

@Repository
@Transactional
public interface GenreRepository extends MongoRepository<Genre, Long> {
    Genre findByGenreName(String genreName);

    Boolean existsByGenreName(String genreName);
}
