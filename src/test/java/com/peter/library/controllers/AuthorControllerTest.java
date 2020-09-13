package com.peter.library.controllers;

import com.peter.library.model.Author;
import com.peter.library.service.AuthorService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.junit.jupiter.api.Assertions.assertNotEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.BDDMockito.given;

@ExtendWith(MockitoExtension.class)
class AuthorControllerTest {

    @Mock
    AuthorService authorService;

    @InjectMocks
    AuthorController controller;

    @BeforeEach
    void setUp() {
        Author author1 = new Author();
        author1.setAuthor("Jane Doe");

        Author author2 = new Author();
        author2.setAuthor("John Doe");
    }

    @Test
    void createAuthor() {
        // given
        Author author1 = new Author();
        author1.setAuthor("Peter Rowling");

        String xmlObject = "<authors>\n" +
                "    <author>Peter Rowling</author>\n" +
                "</authors>";

        given(authorService.createAuthor(xmlObject)).willReturn(author1.toString());

        // when
        String author = controller.createAuthor(xmlObject);

        // then
        assertNotEquals(author, xmlObject);
        assertNotNull(author);
        assertNotNull(author1);
    }

    @Test
    void updateAuthor() {
        // given


        // when


        // then
    }

    @Test
    void deleteAuthorById() {
        // given

        // when

        // then
    }

    @Test
    void findById() {
        // given

        // when

        // then
    }

    @Test
    void getAuthorList() {
        // given

        // when

        // then
    }
}