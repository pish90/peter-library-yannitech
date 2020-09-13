package com.peter.library.dao;

import com.peter.library.dto.AuthorDto;
import com.peter.library.dto.BookDto;
import com.peter.library.dto.ResponseDto;
import com.peter.library.model.Author;
import com.peter.library.model.Book;
import com.peter.library.repo.AuthorRepo;
import com.peter.library.repo.BookRepo;
import org.dozer.DozerBeanMapper;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.ArrayList;
import java.util.List;

@Service
public class BookDao {
    private AuthorRepo authorRepo;
    private BookRepo bookRepo;

    private DozerBeanMapper beanMapper;

    public BookDao(AuthorRepo authorRepo, BookRepo bookRepo) {
        this.authorRepo = authorRepo;
        this.bookRepo = bookRepo;

        beanMapper = new DozerBeanMapper();
    }

    @Transactional
    public ResponseDto createBook(BookDto bookDto) {
        ResponseDto retValue = new ResponseDto();

        try {
            Book book = beanMapper.map(bookDto, Book.class);
            bookRepo.save(book);

            ArrayList<Author> authorArrayList = new ArrayList<>();
            if (!bookDto.getAuthors().isEmpty()) {
                for (AuthorDto authorDto : bookDto.getAuthors()) {
                    Author author = new Author();
                    author.setAuthor(authorDto.getAuthor());
                    authorArrayList.add(author);
                }
                authorRepo.saveAll(authorArrayList);
            }
            retValue.setCode("PASS");
            retValue.setDescription("Book created successfully");
        } catch (Exception e) {
            e.getMessage();
        }
        return retValue;
    }

    public List<Book> findAllBooks() {
        return (List<Book>) bookRepo.findAll();
    }

    public ResponseDto updateBook(BookDto updatedBookDto) {
        ResponseDto retValue = new ResponseDto();

        try {
            Book updatedBook = beanMapper.map(updatedBookDto, Book.class);
            bookRepo.save(updatedBook);

            retValue.setCode("PASS");
            retValue.setDescription("Book updated successfully");
        } catch (Exception e) {
            e.getMessage();
        }
        return retValue;
    }

    public void deleteBookById(int id) {
        bookRepo.deleteById(id);
    }

    public Book findById(int id) {
        return bookRepo.findBookById(id);
    }
}
