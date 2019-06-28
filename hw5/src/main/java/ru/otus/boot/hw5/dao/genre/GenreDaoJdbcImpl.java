package ru.otus.boot.hw5.dao.genre;

import lombok.RequiredArgsConstructor;
import org.springframework.jdbc.core.JdbcOperations;
import org.springframework.stereotype.Repository;
import ru.otus.boot.hw5.mapper.GenreMapper;
import ru.otus.boot.hw5.model.Genre;

import java.util.List;

@Repository
@RequiredArgsConstructor
public class GenreDaoJdbcImpl implements GenreDao {
    private final JdbcOperations jdbc;
    private final GenreMapper mapper;

    @Override
    public int count() {
        return jdbc.queryForObject(" select count(*) from genres", Integer.class);
    }

    @Override
    public void insert(String genre) {
        jdbc.update("insert into genres (genre_name) values (?)", genre);
    }

    @Override
    public Genre getByTitle(String title) {
        return jdbc.queryForObject("select * from genres where genre_name = ?", mapper, title);
    }

    @Override
    public List<Genre> getAll() {
        return jdbc.query("select * from genres", mapper);
    }

    @Override
    public Genre getById(Integer id) {
        return jdbc.queryForObject("select * from genres where id = ?", mapper, id);
    }

    @Override
    public void delete(String title) {
        jdbc.update("delete  from genres where genre_name = ?", title);
    }

    @Override
    public boolean isExist(String title) {
        Integer count = jdbc.queryForObject("select count (*) from genres where genre_name = ?", Integer.class, title);
        return count != null && count > 0;
    }

}
