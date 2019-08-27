package ru.otus.boot.bookstore.repo;


import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;
import ru.otus.boot.bookstore.model.Author;

import java.util.List;

@Transactional
@Repository
public interface AuthorRepository extends MongoRepository<Author, Long> {
    List<Author> findAllByFirstName (String firstName);

    List<Author> findAllByLastName (String lastName);

    Author findAuthorByFirstNameAndLastName(String LastName, String firstName);

    Boolean existsByFirstNameAndLastName(String firstName, String lastName);
}
