package ru.otus.boot.hw7.service.genre;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import ru.otus.boot.hw7.exception.ExistEntityException;
import ru.otus.boot.hw7.model.Genre;
import ru.otus.boot.hw7.repo.GenreRepository;
import ru.otus.boot.hw7.util.Checker;

import javax.transaction.Transactional;
import java.util.List;

@Service
@RequiredArgsConstructor
@Transactional
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
        genreRepository.save(genre);
    }

    @Override
    public void delete(Genre genre) {
        if (!isExist(genre.getGenreName())) {
            throw new ExistEntityException(genre.getGenreName() + " is not exist!");
        }
        genreRepository.delete(genre);
    }
}
