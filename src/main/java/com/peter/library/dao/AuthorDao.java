package com.peter.library.dao;

import com.peter.library.dto.AuthorDto;
import com.peter.library.dto.ResponseDto;
import com.peter.library.model.Author;
import com.peter.library.model.Book;
import com.peter.library.repo.AuthorRepo;
import com.peter.library.repo.BookRepo;
import org.dozer.DozerBeanMapper;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;

@Service
public class AuthorDao {
    private AuthorRepo authorRepo;
    private BookRepo bookRepo;

    private DozerBeanMapper beanMapper;

    public AuthorDao(AuthorRepo authorRepo, BookRepo bookRepo) {
        this.authorRepo = authorRepo;
        this.bookRepo = bookRepo;

        beanMapper = new DozerBeanMapper();
    }

    @Transactional
    public ResponseDto createAuthor(AuthorDto authorDto) {
        ResponseDto retValue = new ResponseDto();

        try {
            Author author = beanMapper.map(authorDto, Author.class);
            authorRepo.save(author);

            for (Book book : author.getBookList()) {
                bookRepo.save(book);
            }

            retValue.setCode("PASS");
            retValue.setDescription("Author created successfully");
        } catch (Exception e) {
            e.getMessage();
        }
        return retValue;
    }

    @Transactional
    public ResponseDto updateAuthor(AuthorDto updatedAuthorDto) {
        ResponseDto retValue = new ResponseDto();

        try {
            Author updatedAuthor = beanMapper.map(updatedAuthorDto, Author.class);
            authorRepo.save(updatedAuthor);

            retValue.setCode("PASS");
            retValue.setDescription("Author updated successfully");
        } catch (Exception e) {
            e.getMessage();
        }
        return retValue;
    }

    public List<Author> findAllAuthors() {
        return (List<Author>) authorRepo.findAll();
    }

    public Author findById(int id) {
        return authorRepo.findAuthorById(id);
    }

    public void deleteAuthorById(int id) {
        authorRepo.deleteById(id);
    }
}
