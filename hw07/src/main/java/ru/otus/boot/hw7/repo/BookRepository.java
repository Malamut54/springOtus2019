package ru.otus.boot.hw7.repo;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import ru.otus.boot.hw7.model.Book;

import java.util.List;

public interface BookRepository extends JpaRepository<Book, Long> {
    List<Book> getAllByBookName(String bookName);

    List<Book> getAllByAuthor_FirstName(String firstName);

    List<Book> getAllByAuthor_LastName(String lastName);

    @Query("select case when count(b) > 0 then true else false end from Book b " +
            "where b.bookName = :bookName " +
            "and b.author.firstName = :firstName " +
            "and b.author.lastName = :lastName " +
            "and b.genre.genreName = :genreName")
    Boolean existsByBookNameAndAuthorAndGenre(
            @Param("bookName") String bookName,
            @Param("firstName") String firstName,
            @Param("lastName") String lastName,
            @Param("genreName") String genreName);
}

