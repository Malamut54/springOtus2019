package ru.otus.boot.bookstore.service;

import ru.otus.boot.bookstore.model.Genre;

import java.util.List;

public interface GenreService {
    List<Genre> getAll();

    Genre getByTitle(String title);

    boolean isExist(String title);

    void insert(Genre genre);

    void delete(Genre genre);

}
