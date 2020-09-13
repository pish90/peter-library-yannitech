package com.peter.library.repo;

import com.peter.library.model.Book;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface BookRepo extends CrudRepository<Book, Integer> {
    public Book findBookById(int id);
}
