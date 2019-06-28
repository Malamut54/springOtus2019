package ru.otus.boot.hw5.mapper;


import lombok.RequiredArgsConstructor;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Service;
import ru.otus.boot.hw5.model.Author;
import ru.otus.boot.hw5.model.Book;
import ru.otus.boot.hw5.model.Genre;

import java.sql.ResultSet;
import java.sql.SQLException;

@Service
@RequiredArgsConstructor
public class BookMapper implements RowMapper<Book> {
    private final AuthorMapper authorMapper;
    private final GenreMapper genreMapper;

    @Override
    public Book mapRow(ResultSet rs, int i) throws SQLException {
        Author author = authorMapper.mapRow(rs, i);
        Genre genre = genreMapper.mapRow(rs, i);
        return new Book(author, genre, rs.getString("book_name"));
    }
}
