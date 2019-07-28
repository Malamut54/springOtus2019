package ru.otus.boot.hw6.shell;

import lombok.RequiredArgsConstructor;
import org.springframework.shell.standard.ShellComponent;
import org.springframework.shell.standard.ShellMethod;
import ru.otus.boot.hw6.exception.ExistEntityException;
import ru.otus.boot.hw6.model.Genre;
import ru.otus.boot.hw6.service.genre.GenreService;

import java.util.List;

@ShellComponent
@RequiredArgsConstructor
public class GenreShellService {
    private final GenreService genreService;

    @ShellMethod("Get all genres")
    public List<Genre> getAllGenre() {
        return genreService.getAll();
    }

    @ShellMethod("Get genres by title")
    public Genre getGenreByTitle(String title) {
        return genreService.getByTitle(title);
    }

    @ShellMethod("Is genre exist")
    public boolean checkGenre(String title) {
        return genreService.isExist(title);
    }

    @ShellMethod("Insert Genre")
    public void insertGenre(String title) {
        if (genreService.isExist(title)) {
            throw new ExistEntityException("Genre is exist");
        }
        genreService.insert(new Genre(title));
    }
}
