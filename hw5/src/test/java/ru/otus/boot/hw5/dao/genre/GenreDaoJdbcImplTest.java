package ru.otus.boot.hw5.dao.genre;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.shell.jline.InteractiveShellApplicationRunner;
import org.springframework.shell.jline.ScriptShellApplicationRunner;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;
import ru.otus.boot.hw5.model.Genre;

import static org.assertj.core.api.Assertions.assertThat;

@ExtendWith(SpringExtension.class)
@SpringBootTest(properties = {
        ScriptShellApplicationRunner.SPRING_SHELL_SCRIPT_ENABLED + "=false",
        InteractiveShellApplicationRunner.SPRING_SHELL_INTERACTIVE_ENABLED + "=false"
})
@Transactional(propagation = Propagation.NOT_SUPPORTED)
class GenreDaoJdbcImplTest {
    @Autowired
    private GenreDao genreDao;

    @Test
    public void testCount() {
        assertThat(genreDao.count()).isEqualTo(1);
    }

    @Test
    public void testGetByTitle() {
        assertThat(genreDao.getByTitle("horror")).isEqualTo(new Genre("horror"));
    }

    @Test
    public void testGetAll() {
        assertThat(genreDao.getAll())
                .hasSize(1)
                .contains(new Genre("horror"));
    }

    @Test
    public void testGetById() {
        assertThat(genreDao.getById(1)).isEqualTo(new Genre("horror"));
    }

    @Test
    public void testInsert() {
        genreDao.insert("roman");
        assertThat(genreDao.getByTitle("roman")).isEqualTo(new Genre("roman"));
        genreDao.delete("roman");
    }

    @Test
    public void testDelete() {
        genreDao.insert("roman");
        assertThat(genreDao.getByTitle("roman")).isEqualTo(new Genre("roman"));
        genreDao.delete("roman");
        assertThat(genreDao.getAll()).hasSize(1).doesNotContain(new Genre("roman"));
    }

    @Test
    public void testIsExist() {
        assertThat(genreDao.isExist("horror")).isTrue();
    }
}