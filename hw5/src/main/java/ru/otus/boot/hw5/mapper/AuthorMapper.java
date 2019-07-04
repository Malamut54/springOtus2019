package ru.otus.boot.hw5.mapper;

import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Service;
import ru.otus.boot.hw5.model.Author;

import java.sql.ResultSet;
import java.sql.SQLException;

@Service
public class AuthorMapper implements RowMapper<Author> {
    @Override
    public Author mapRow(ResultSet resultSet, int i) throws SQLException {
        String firstName = resultSet.getString("first_name");
        String lastName = resultSet.getString("last_name");
        return new Author(firstName, lastName);
    }
}
