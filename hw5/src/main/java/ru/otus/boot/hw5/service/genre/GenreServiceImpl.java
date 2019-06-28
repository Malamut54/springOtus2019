package ru.otus.boot.hw5.service.genre;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import ru.otus.boot.hw5.dao.author.AuthorDao;
import ru.otus.boot.hw5.dao.genre.GenreDao;
import ru.otus.boot.hw5.exception.EmptyListException;
import ru.otus.boot.hw5.exception.ExistEntityException;
import ru.otus.boot.hw5.model.Genre;

import java.util.List;

@Service
@RequiredArgsConstructor
public class GenreServiceImpl implements GenreService {
    private final GenreDao dao;

    @Override
    public List<Genre> getAll() {
        List<Genre> genres = dao.getAll();

        if (genres.isEmpty()) {
            throw new EmptyListException("Genres Not Found");
        }
        return genres;
    }

    @Override
    public Genre getByTitle(String title) {
        if (isExist(title)) {
            return dao.getByTitle(title);
        } else {
            throw new ExistEntityException(title + "not found");
        }
    }

    @Override
    public boolean isExist(String title) {
        return dao.isExist(title);
    }

    @Override
    public void insert(Genre genre) {
        dao.insert(genre.getGenreName());
    }

    @Override
    public void delete(Genre genre) {
        dao.delete(genre.getGenreName());
    }
}
