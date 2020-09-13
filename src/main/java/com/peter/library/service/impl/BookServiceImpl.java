package com.peter.library.service.impl;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.dataformat.xml.XmlMapper;
import com.peter.library.dao.BookDao;
import com.peter.library.dto.BookDto;
import com.peter.library.dto.ResponseDto;
import com.peter.library.model.Book;
import com.peter.library.service.BookService;
import org.dozer.DozerBeanMapper;
import org.springframework.stereotype.Service;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class BookServiceImpl implements BookService {
    private BookDao bookDao;

    private DozerBeanMapper beanMapper;
    private XmlMapper xmlMapper;

    public BookServiceImpl(BookDao bookDao) {
        this.bookDao = bookDao;

        beanMapper = new DozerBeanMapper();
        xmlMapper = new XmlMapper();
    }

    /**
     * This function creates a book
     *
     * @param xmlObject XML representation of BookDto
     * @return ResponseDto
     */
    @Override
    public String createBook(String xmlObject) {
        String retValue = null;

        try {
            BookDto bookDto = xmlMapper.readValue(xmlObject, BookDto.class);

            // write value to db
            ResponseDto responseDto = bookDao.createBook(bookDto);

            if (responseDto.getCode().equalsIgnoreCase("PASS")) {
                retValue = "Book created successfully";
            } else {
                retValue = "Failed to create book";
            }
        } catch (JsonProcessingException jpe) {
            jpe.getMessage();
        }
        return retValue;
    }

    @Override
    public BookDto updateBook(String xmlObject) {
        BookDto retValue = null;

        ResponseDto responseDto = new ResponseDto();

        try {
            BookDto bookDto = xmlMapper.readValue(xmlObject, BookDto.class);

            // get all records from db
            List<BookDto> bookDtoList = getBookList();

            // compare each record with what is being passed by the user
            for (BookDto updatedBook : bookDtoList) {
                if (!bookDto.equals(updatedBook)) {
                    updatedBook.setCategory(bookDto.getCategory());
                    updatedBook.setTitle(bookDto.getTitle());
                    updatedBook.setLang(bookDto.getLang());
                    updatedBook.setYear(bookDto.getYear());
                    updatedBook.setPrice(bookDto.getPrice());
                    updatedBook.setAuthors(bookDto.getAuthors());
                }
                responseDto = bookDao.updateBook(updatedBook);
                if (responseDto.getCode().equalsIgnoreCase("PASS")) {
                    retValue = updatedBook;
                }
            }
        } catch (IOException ioe) {
            ioe.getMessage();
        }
        return retValue;
    }

    @Override
    public void deleteBookById(int id) {
        bookDao.deleteBookById(id);
    }

    @Override
    public Book findById(int id) {
        return bookDao.findById(id);
    }

    @Override
    public List<BookDto> getBookList() {
        List<BookDto> bookDtoList = new ArrayList<>();
        BookDto bookDto;

        try {
            List<Book> bookList = bookDao.findAllBooks();
            for (Book book : bookList) {
                bookDto = beanMapper.map(book, BookDto.class);
                bookDtoList.add(bookDto);
            }
        } catch (Exception e) {
            e.getMessage();
        }
        return bookDtoList;
    }

    @Override
    public String readBookStore() {
        String retValue = null;

        try (InputStream inputStream = getClass().getResourceAsStream("/library.xml");
             BufferedReader reader = new BufferedReader(new InputStreamReader(inputStream))) {
            retValue = reader.lines()
                    .collect(Collectors.joining(System.lineSeparator()));
        } catch (IOException ex) {
            ex.getMessage();
        }
        return retValue;
    }
}

