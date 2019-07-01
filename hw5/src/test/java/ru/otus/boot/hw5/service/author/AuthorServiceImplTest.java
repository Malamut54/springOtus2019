package ru.otus.boot.hw5.service.author;

import org.junit.jupiter.api.Test;
import ru.otus.boot.hw5.dao.author.AuthorDao;
import ru.otus.boot.hw5.exception.EmptyListException;
import ru.otus.boot.hw5.model.Author;

import java.util.Collections;

import static org.assertj.core.api.Assertions.*;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

class AuthorServiceImplTest {
    private AuthorDao authorDao = mock(AuthorDao.class);
    private AuthorService authorService = new AuthorServiceImpl(authorDao);

    @Test
    public void testGetAllSuccess() {
        when(authorDao.getAll()).thenReturn(Collections.singletonList(new Author("author", "author")));
        assertThat(authorService.getAll()).hasSize(1).contains(new Author("author", "author"));
    }

    @Test
    public void testGetAllExceptionWhenEmptyList() {
        when(authorDao.getAll()).thenReturn(Collections.EMPTY_LIST);
        assertThatThrownBy(() -> authorService.getAll())
                .isInstanceOf(EmptyListException.class)
                .hasMessage("Authors not found");
    }

    @Test
    public void testGetByFirstNameSuccess() {

    }
}