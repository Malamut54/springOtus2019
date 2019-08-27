package ru.otus.boot.bookstore.repo;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;
import ru.otus.boot.bookstore.model.Book;

import java.util.List;

@Transactional
@Repository
public interface BookRepository extends MongoRepository<Book, Long> {
    List<Book> getBooksByBookName(String bookName);

    List<Book> getBooksByAuthor_LastName(String firstName);

    List<Book> getBooksByAuthor_FirstName(String lastName);

//    @Query("select case when count(b) > 0 then true else false end from Book b " +
//            "where b.bookName = :bookName " +
//            "and b.author.firstName = :firstName " +
//            "and b.author.lastName = :lastName " +
//            "and b.genre.genreName = :genreName")
    @Query("{'book.bookName': ?0, 'author.lastName': ?1, 'author.lastName': ?2, 'genre.genreName': ?3}")
    Book existsByBookNameAndAuthorAndGenre(
            String bookName,
            String firstName,
            String lastName,
            String genreName);
}

