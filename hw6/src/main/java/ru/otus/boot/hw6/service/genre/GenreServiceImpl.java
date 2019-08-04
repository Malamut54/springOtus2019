package ru.otus.boot.hw6.service.genre;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import ru.otus.boot.hw6.exception.ExistEntityException;
import ru.otus.boot.hw6.model.Genre;
import ru.otus.boot.hw6.repo.GenreRepository;
import ru.otus.boot.hw6.util.Checker;

import java.util.List;

@Service
@RequiredArgsConstructor
public class GenreServiceImpl implements GenreService {
    private final GenreRepository genreRepository;

    @Override
    public List<Genre> getAll() {
        return Checker.checkEmptyList(genreRepository.getAll());
    }

    @Override
    public Genre getByTitle(String title) {
        if (isExist(title)) {
            return genreRepository.getByTitle(title);
        } else {
            throw new ExistEntityException(title + " is not exist!");
        }
    }

    @Override
    public boolean isExist(String title) {
        return genreRepository.isExist(title);
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
        genreRepository.delete(genre.getGenreName());
    }
}
