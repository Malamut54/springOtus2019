package ru.otus.boot.hw5.dao.author;


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

import static org.assertj.core.api.Assertions.assertThat;

@ExtendWith(SpringExtension.class)
@SpringBootTest(properties = {
        ScriptShellApplicationRunner.SPRING_SHELL_SCRIPT_ENABLED + "=false",
        InteractiveShellApplicationRunner.SPRING_SHELL_INTERACTIVE_ENABLED + "=false"
})
@Transactional(propagation = Propagation.NOT_SUPPORTED)
public class AuthorDaoJdbcImplTest {
    @Autowired
    private AuthorDao authorDao;

    @Test
    public void testCount() {
        assertThat(authorDao.count()).isEqualTo(1L);
    }

    @Test
    public void testGetByFirstName() {
        assertThat(authorDao.getByFirstName("Stephen"))
                .hasSize(1)
                .contains(new Author("Stephen", "King"));
    }

    @Test
    public void testGetByLastName() {
        assertThat(authorDao.getByLastName("King"))
                .hasSize(1)
                .contains(new Author("Stephen", "King"));
    }

    @Test
    public void testGetById() {
        assertThat(authorDao.getById(1)).isEqualTo(new Author("Stephen", "King"));
    }

    @Test
    public void testInsert() {
        Author testAuthor = new Author("Test", "Testovich");
        authorDao.insert(testAuthor.getFirstName(), testAuthor.getLastName());
        assertThat(authorDao.getByFirstName("Test"))
                .hasSize(1)
                .contains(testAuthor);
        assertThat(authorDao.getByLastName("Testovich"))
                .hasSize(1)
                .contains(testAuthor);
        assertThat(authorDao.isExist(testAuthor.getFirstName(), testAuthor.getLastName())).isTrue();

        authorDao.delete(testAuthor.getFirstName(), testAuthor.getLastName());
    }

    @Test
    public void testDelete() {
        Author testAuthor = new Author("Test", "Testovich");
        authorDao.insert(testAuthor.getFirstName(), testAuthor.getLastName());
        assertThat(authorDao.getAll()).hasSize(2).contains(testAuthor);
        authorDao.delete(testAuthor.getFirstName(), testAuthor.getLastName());
        assertThat(authorDao.getAll()).hasSize(1).doesNotContain(testAuthor);
    }

    @Test
    public void testIsExist() {
        assertThat(authorDao.isExist("Stephen", "King")).isTrue();
    }
}
