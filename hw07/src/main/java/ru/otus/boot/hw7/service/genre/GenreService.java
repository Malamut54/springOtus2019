package ru.otus.boot.hw7.service.genre;


import ru.otus.boot.hw7.model.Genre;

import java.util.List;

public interface GenreService {
    List<Genre> getAll();

    Genre getByTitle(String Title);

    boolean isExist(String title);

    void insert(Genre genre);

    void delete(Genre genre);

}
