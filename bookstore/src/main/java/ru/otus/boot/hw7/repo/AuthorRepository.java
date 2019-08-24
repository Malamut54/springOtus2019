package ru.otus.boot.hw7.repo;

import org.springframework.data.jpa.repository.JpaRepository;
import ru.otus.boot.hw7.model.Author;

import java.util.List;

public interface AuthorRepository extends JpaRepository <Author, Long> {
    List<Author> findAllByFirstName (String firstName);

    List<Author> findAllByLastName (String lastName);

    Author findAuthorByFirstNameAndLastName(String LastName, String firstName);

    Boolean existsByFirstNameAndLastName(String firstName, String lastName);
}
