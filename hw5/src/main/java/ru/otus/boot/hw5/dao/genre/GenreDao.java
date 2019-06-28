package ru.otus.boot.hw5.dao.genre;

import ru.otus.boot.hw5.model.Genre;

import java.util.List;

public interface GenreDao {
    int count();

    void insert(String genre);

    Genre getByTitle(String title);

    List<Genre> getAll();

    Genre getById(Integer id);

    void delete(String title);

    boolean isExist(String title);
}
