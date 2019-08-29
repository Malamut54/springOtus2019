package ru.otus.boot.bookstore.repo;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Query;
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

    @Query("{'bookName': ?0, 'author.firstName': ?1, 'author.lastName': ?2, 'genre.genreName': ?3}")
    Book findBookByAllParam(
            String bookName,
            String firstName,
            String lastName,
            String genreName);

    Book getBooksById(String id);
}

