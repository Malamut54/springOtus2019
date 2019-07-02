package ru.otus.boot.hw5.dao.author;

import lombok.RequiredArgsConstructor;
import org.springframework.jdbc.core.JdbcOperations;
import org.springframework.stereotype.Repository;
import ru.otus.boot.hw5.mapper.AuthorMapper;
import ru.otus.boot.hw5.model.Author;

import java.util.List;

@Repository
@RequiredArgsConstructor
public class AuthorDaoJdbcImpl implements AuthorDao {
    private final JdbcOperations jdbc;
    private final AuthorMapper mapper;

    @Override
    public int count() {
    return jdbc.queryForObject(" select count(*) from authors", Integer.class);

    }

    @Override
    public void insert(String firstName, String lastName) {
        jdbc.update("insert into authors(first_name, last_name) values (?, ?)", firstName, lastName);
    }

    @Override
    public List<Author> getByFirstName(String firstName) {
        return jdbc.query("select * from authors where first_name = ?", mapper, firstName);
    }

    @Override
    public List<Author> getByLastName(String lastName) {
        return jdbc.query("select * from authors where last_name = ?", mapper, lastName);
    }

    @Override
    public List<Author> getAll() {
        return jdbc.query("select * from authors", mapper);
    }

    @Override
    public Author getById(Long id) {
        return jdbc.queryForObject("select * from authors where id = ?", mapper, id);
    }

    @Override
    public Author getByFirstAndLastName(String firstName, String lastname) {
        return jdbc.queryForObject("select * from authors where first_name = ? and last_name = ?", mapper, firstName, lastname);
    }

    @Override
    public void delete(String firstName, String lastName) {
        jdbc.update("delete from authors where first_name = ? and last_name = ?", firstName, lastName);
    }

    @Override
    public boolean isExist(String firstName, String lastName) {
        String sql = "select count(*) from authors where first_name = ? and last_name = ?";
        Integer count = jdbc.queryForObject(sql, Integer.class, firstName, lastName);
        return count != null && count > 0;
    }
}
