package ru.otus.boot.hw5.mapper;

import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Service;
import ru.otus.boot.hw5.model.Genre;

import java.sql.ResultSet;
import java.sql.SQLException;

@Service
public class GenreMapper implements RowMapper<Genre> {
    @Override
    public Genre mapRow(ResultSet resultSet, int i) throws SQLException {
        return new Genre(resultSet.getString("genre_name"));

    }
}
