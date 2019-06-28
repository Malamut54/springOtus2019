package ru.otus.boot.hw5.service.genre;

import ru.otus.boot.hw5.model.Genre;

import java.util.List;

public interface GenreService {
    List<Genre> getAll();

    Genre getByTitle(String Title);

    boolean isExist(String title);

    void insert(Genre genre);

    void delete(Genre genre);
}
