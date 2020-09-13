package com.peter.library.repo;

import com.peter.library.model.Author;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface AuthorRepo extends CrudRepository<Author, Integer> {
    Author findAuthorById(int id);
}
