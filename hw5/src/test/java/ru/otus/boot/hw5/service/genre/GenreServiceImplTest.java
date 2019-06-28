package ru.otus.boot.hw5.service.genre;

import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import ru.otus.boot.hw5.dao.genre.GenreDao;
import ru.otus.boot.hw5.model.Genre;

import static org.assertj.core.api.Assertions.assertThat;

class GenreServiceImplTest {
    @Mock
    private GenreDao genreDao;
    @InjectMocks
    private GenreService genreService;


    @Test
    public void testGetAllSuccess() {
        assertThat(genreService.getAll()).hasSize(1).contains(new Genre("horror"));
    }
}