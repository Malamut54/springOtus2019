package ru.otus.boot.hw7.repo;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import ru.otus.boot.hw7.model.Genre;

@Repository
public interface GenreRepository extends JpaRepository<Genre, Long> {
    Genre findByGenreName(String genreName);

    Boolean existsByGenreName(String genreName);
}
