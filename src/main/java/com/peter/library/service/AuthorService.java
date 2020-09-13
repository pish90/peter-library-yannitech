package com.peter.library.service;

import com.peter.library.dto.AuthorDto;
import com.peter.library.model.Author;

import java.util.List;

public interface AuthorService {
    String createAuthor(String xmlObject);
    AuthorDto updateAuthor(String xmlObject);
    void deleteAuthorById(int id);
    Author findById(int id);

    List<AuthorDto> getAuthorList();
}
