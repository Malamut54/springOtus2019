package ru.otus.boot.hw5.dao.book;

import lombok.RequiredArgsConstructor;
import org.springframework.jdbc.core.JdbcOperations;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.jdbc.core.namedparam.SqlParameterSource;
import org.springframework.stereotype.Repository;
import ru.otus.boot.hw5.mapper.BookMapper;
import ru.otus.boot.hw5.model.Book;

import java.util.List;

@Repository
@RequiredArgsConstructor
public class BookDaoJdbcImpl implements BookDao {
    private final NamedParameterJdbcTemplate namedJdbc;
    private final JdbcOperations jdbc;
    private final BookMapper mapper;

    @Override
    public int count() {
        return jdbc.queryForObject("select count(*) from books", Integer.class);
    }

    @Override
    public void insert(String bookName, String firstName, String lastName, String genreName) {
        String sql = "insert into books (book_name, author_id, genre_id) values " +
                "(:book_name, (select id from authors where first_name = :first_name and last_name = :last_name)" +
                ", (select id from genres where genre_name = :genre_title))";
        SqlParameterSource parameters = new MapSqlParameterSource()
                .addValue("book_name", bookName)
                .addValue("first_name",firstName)
                .addValue("last_name", lastName)
                .addValue("genre_title", genreName);

        namedJdbc.update(sql, parameters);
    }

    @Override
    public List<Book> getByFirstName(String firstName) {
        String sql = "select book_name, first_name, last_name, genre_name from books b" +
                "  inner join authors a on b.author_id = a.id" +
                "  inner join genres g on b.genre_id = g.id" +
                " where a.first_name = ?";
        return jdbc.query(sql, mapper, firstName);
    }

    @Override
    public List<Book> getByLastName(String lastName) {
        String sql = "select book_name, first_name, last_name, genre_name from books b" +
                "  inner join authors a on b.author_id = a.id" +
                "  inner join genres g on b.genre_id = g.id" +
                " where a.last_name = ?";
        return jdbc.query(sql, mapper, lastName);
    }

    @Override
    public List<Book> getAll() {
        String sql = "select book_name, first_name, last_name, genre_name from books b" +
                "  inner join authors a on b.author_id = a.id" +
                "  inner join genres g on b.genre_id = g.id";
        return jdbc.query(sql, mapper);
    }

    @Override
    public List<Book> getByTitle(String bookName) {
        String sql = "select book_name, first_name, last_name, genre_name from books b" +
                "  inner join authors a on b.author_id = a.id" +
                "  inner join genres g on b.genre_id = g.id" +
                " where b.book_name = ?";
        return jdbc.query(sql, mapper, bookName);
    }

    @Override
    public void delete(String bookName, String firstName, String lastName, String genreName) {
        String sql = "delete from books b where b.book_name = ?" +
                " and b.author_id = (select id from authors a where a.first_name = ?" +
                    " and last_name = ?)" +
                " and b.genre_id = (select id from genres g where g.genre_name = ?)";

        jdbc.update(sql, bookName, firstName, lastName, genreName);
    }

    @Override
    public boolean isExist(String bookName, String firstName, String lastName, String genreName) {
        String sql = "select count(*) from books b" +
                " inner join authors a on b.author_id = a.id" +
                " inner join genres g on b.genre_id = g.id " +
                " where b.book_name = :book_name" +
                " and a.first_name = :first_name" +
                " and a.last_name = :last_name" +
                " and g.genre_name = :genre_title";

        SqlParameterSource parameters = new MapSqlParameterSource()
                .addValue("book_name", bookName)
                .addValue("first_name",firstName)
                .addValue("last_name", lastName)
                .addValue("genre_title", genreName);
        Integer count = namedJdbc.queryForObject(sql, parameters, Integer.class);
        return count != null && count > 0;

    }
}
