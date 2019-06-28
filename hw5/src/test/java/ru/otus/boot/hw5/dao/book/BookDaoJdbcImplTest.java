package ru.otus.boot.hw5.dao.book;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.shell.jline.InteractiveShellApplicationRunner;
import org.springframework.shell.jline.ScriptShellApplicationRunner;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;
import ru.otus.boot.hw5.model.Author;
import ru.otus.boot.hw5.model.Book;
import ru.otus.boot.hw5.model.Genre;

import static org.assertj.core.api.Assertions.assertThat;

@ExtendWith(SpringExtension.class)
@SpringBootTest(properties = {
        ScriptShellApplicationRunner.SPRING_SHELL_SCRIPT_ENABLED + "=false",
        InteractiveShellApplicationRunner.SPRING_SHELL_INTERACTIVE_ENABLED + "=false"
})
@Transactional(propagation = Propagation.NOT_SUPPORTED)
class BookDaoJdbcImplTest {
    @Autowired
    private BookDao bookDao;

    private Genre genre = new Genre("horror");
    private Author author = new Author("Stephen", "King");
    private Book book = new Book(author, genre, "Sunshine");

    @Test
    public void testCount() {
        assertThat(bookDao.count()).isEqualTo(1L);
    }
    
    @Test
    public void testGetByFirstName() {
        assertThat(bookDao.getByFirstName("Stephen")).hasSize(1).contains(book);
    }

    @Test
    public void testGetBylastName() {
        assertThat(bookDao.getByLastName("King")).hasSize(1).contains(book);
    }
    
    @Test
    public void testGetBybookName() {
        assertThat(bookDao.getByTitle("Sunshine")).hasSize(1).contains(book);
    }

    @Test
    public void testGetAll() {
        Genre genre = new Genre("horror");
        Author author = new Author("Stephen", "King");
        Book book1 = new Book(author, genre, "book");

        bookDao.insert("book", "Stephen", "King", "horror");
        assertThat(bookDao.getAll()).hasSize(2).contains(book, book1);
        bookDao.delete("book");
    }

    @Test
    public void testInsert() {
        Genre genre = new Genre("horror");
        Author author = new Author("Stephen", "King");
        Book book1 = new Book(author, genre, "book");

        bookDao.insert("book", "Stephen", "King", "horror");
        assertThat(bookDao.getByTitle("book")).hasSize(1).contains(book1);
        bookDao.delete("book");
        assertThat(bookDao.getAll()).hasSize(1).contains(book);
    }

    @Test
    public void testDelete() {
        Genre genre = new Genre("horror");
        Author author = new Author("Stephen", "King");
        Book book1 = new Book(author, genre, "book");

        bookDao.insert("book", "Stephen", "King", "horror");
        assertThat(bookDao.getAll()).hasSize(2).contains(book, book1);
        bookDao.delete("book");
    }
}