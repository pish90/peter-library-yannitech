package com.peter.library.service;

import com.peter.library.dto.BookDto;
import com.peter.library.model.Book;

import java.util.List;

public interface BookService {
    String createBook(String xmlObject);
    BookDto updateBook(String xmlObject);
    void deleteBookById(int id);
    Book findById(int id);

    List<BookDto> getBookList();

    String readBookStore();

}
