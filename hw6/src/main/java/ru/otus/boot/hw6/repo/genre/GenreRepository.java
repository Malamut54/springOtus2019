package ru.otus.boot.hw6.repo.genre;

import ru.otus.boot.hw6.model.Genre;

import java.util.List;

public interface GenreRepository {
    void insert(Genre genre);

    Genre getByTitle(String title);

    List<Genre> getAll();

    void delete(String title);

    boolean isExist(String title);
}
