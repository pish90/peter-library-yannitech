package com.peter.library.controllers;

import com.peter.library.dto.AuthorDto;
import com.peter.library.model.Author;
import com.peter.library.service.AuthorService;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@RequestMapping(value = "/author")
public class AuthorController {
    private AuthorService authorService;

    public AuthorController(AuthorService authorService) {
        this.authorService = authorService;
    }

    @PostMapping(value = "/create")
    @ResponseBody
    public String createAuthor(@RequestBody String xmlObject) {
        return authorService.createAuthor(xmlObject);
    }

    @PutMapping(value = "/update")
    @ResponseBody
    public AuthorDto updateAuthor(@RequestBody String xmlObject) {
        return authorService.updateAuthor(xmlObject);
    }

    @DeleteMapping(value = "/delete/{id}")
    @ResponseBody
    public void deleteAuthorById(@PathVariable Integer id) {
        authorService.deleteAuthorById(id);
    }

    @GetMapping(value = "findById/{id}")
    @ResponseBody
    public Author findById(@PathVariable Integer id) {
        return authorService.findById(id);
    }

    @GetMapping(value = "/list")
    @ResponseBody
    public List<AuthorDto> getAuthorList() {
        return authorService.getAuthorList();
    }
}
