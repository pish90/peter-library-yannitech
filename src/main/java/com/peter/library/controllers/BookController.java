package com.peter.library.controllers;

import com.peter.library.dto.BookDto;
import com.peter.library.model.Book;
import com.peter.library.service.BookService;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@RequestMapping(value = "/book")
public class BookController {
    private BookService bookService;

    public BookController(BookService bookService) {
        this.bookService = bookService;
    }

    @PostMapping(value = "/create")
    @ResponseBody
    public String createBook(@RequestBody String xmlObject) {
        return bookService.createBook(xmlObject);
    }

    @PutMapping(value = "/update")
    @ResponseBody
    public BookDto updateBook(@RequestBody String xmlObject) {
        return bookService.updateBook(xmlObject);
    }

    @DeleteMapping(value = "/delete/{id}")
    @ResponseBody
    public void deleteBookById(@PathVariable Integer id) {
        bookService.deleteBookById(id);
    }

    @GetMapping(value = "findById/{id}")
    @ResponseBody
    public Book findBookById(@PathVariable Integer id) {
        return bookService.findById(id);
    }

    @GetMapping(value = "/list")
    @ResponseBody
    public List<BookDto> getBookList() {
        return bookService.getBookList();
    }

    @GetMapping(value = "/bookStore")
    @ResponseBody
    public String readBookStore() {
        return bookService.readBookStore();
    }
}
