package ru.otus.boot.hw5.service.book;

import org.junit.jupiter.api.Test;
import ru.otus.boot.hw5.dao.book.BookDao;
import ru.otus.boot.hw5.exception.EmptyListException;
import ru.otus.boot.hw5.model.Author;
import ru.otus.boot.hw5.model.Book;
import ru.otus.boot.hw5.model.Genre;

import java.util.Collections;

import static org.assertj.core.api.Assertions.*;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

class BookServiceImplTest {
    private BookDao bookDao = mock(BookDao.class);
    private BookService bookService = new BookServiceImpl(bookDao);

    private Book book = new Book(new Author("firstName", "lastName"), new Genre("genre"), "bookName");

    @Test
    public void getAllSuccess() {
        when(bookDao.getAll()).thenReturn(Collections.singletonList(book));
        assertThat(bookService.getAll()).hasSize(1).contains(book);
    }

    @Test
    public void testGetAllExceptionWhenEmptyList() {
        when(bookDao.getAll()).thenReturn(Collections.EMPTY_LIST);
        assertThatThrownBy(() -> bookService.getAll())
                .isInstanceOf(EmptyListException.class)
                .hasMessage("Books not found");
    }

    @Test
    public void testGetByTitleSuccess() {
        when(bookDao.getByTitle("bookName")).thenReturn(Collections.singletonList(book));
        assertThat(bookService.getByTitle("bookName")).hasSize(1).contains(book);
    }

    @Test
    public void testGetByTitleWhenEnmptyList() {
        when(bookDao.getByTitle("asd")).thenReturn(Collections.EMPTY_LIST);
        assertThatThrownBy(() -> bookService.getByTitle("asd"))
                .isInstanceOf(EmptyListException.class)
                .hasMessage("Books not found asd");
    }
}