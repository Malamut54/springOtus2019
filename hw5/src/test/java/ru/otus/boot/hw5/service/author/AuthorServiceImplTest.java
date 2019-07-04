package ru.otus.boot.hw5.service.author;

import lombok.RequiredArgsConstructor;
import org.junit.jupiter.api.Test;
import org.springframework.stereotype.Service;
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
        when(authorDao.getByFirstName("name")).thenReturn(Collections.singletonList(new Author("name", "name")));
        assertThat(authorService.getByFirstName("name")).hasSize(1).contains(new Author("name", "name"));
    }

    @Test
    public void testGetByFirstNameWhenEmptyList() {
        when(authorDao.getByFirstName("name")).thenReturn(Collections.EMPTY_LIST);
        assertThatThrownBy(() -> authorService.getByFirstName("name"))
                .isInstanceOf(EmptyListException.class)
                .hasMessage("Authors not found for first name name");
    }

    @Test
    public void testGetByLastNameSuccess() {
        when(authorDao.getByLastName("name")).thenReturn(Collections.singletonList(new Author("name", "name")));
        assertThat(authorService.getByLastName("name")).hasSize(1).contains(new Author("name", "name"));
    }

    @Test
    public void testGetByLastNameWhenEmptyList() {
        when(authorDao.getByLastName("name")).thenReturn(Collections.EMPTY_LIST);
        assertThatThrownBy(() -> authorService.getByLastName("name"))
                .isInstanceOf(EmptyListException.class)
                .hasMessage("Authors not found for last name name");
    }

}