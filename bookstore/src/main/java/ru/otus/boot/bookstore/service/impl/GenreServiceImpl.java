package ru.otus.boot.bookstore.service.impl;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import ru.otus.boot.bookstore.exception.ExistEntityException;
import ru.otus.boot.bookstore.model.Genre;
import ru.otus.boot.bookstore.repo.GenreRepository;
import ru.otus.boot.bookstore.service.GenreService;
import ru.otus.boot.bookstore.util.Checker;

import java.util.List;

@Service
@RequiredArgsConstructor
public class GenreServiceImpl implements GenreService {
    private final GenreRepository genreRepository;

    @Override
    public List<Genre> getAll() {
        return Checker.checkEmptyList(genreRepository.findAll());
    }

    @Override
    public Genre getByTitle(String genreName) {
        if (isExist(genreName)) {
            return genreRepository.findByGenreName(genreName);
        } else {
            throw new ExistEntityException(genreName + " is not exist!");
        }
    }

    @Override
    public boolean isExist(String genreName) {
        return genreRepository.existsByGenreName(genreName);
    }

    @Override
    public void insert(Genre genre) {
        genreRepository.insert(genre);
    }

    @Override
    public void delete(Genre genre) {
        if (!isExist(genre.getGenreName())) {
            throw new ExistEntityException(genre.getGenreName() + " is not exist!");
        }
        genreRepository.delete(genre);
    }
}
