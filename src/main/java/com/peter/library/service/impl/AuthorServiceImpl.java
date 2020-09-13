package com.peter.library.service.impl;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.dataformat.xml.XmlMapper;
import com.peter.library.dao.AuthorDao;
import com.peter.library.dto.AuthorDto;
import com.peter.library.dto.ResponseDto;
import com.peter.library.model.Author;
import com.peter.library.service.AuthorService;
import org.dozer.DozerBeanMapper;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

@Service
public class AuthorServiceImpl implements AuthorService {
    private AuthorDao authorDao;

    private DozerBeanMapper beanMapper;
    private XmlMapper xmlMapper;

    public AuthorServiceImpl(AuthorDao authorDao) {
        this.authorDao = authorDao;

        beanMapper = new DozerBeanMapper();
        xmlMapper = new XmlMapper();
    }

    /**
     * This function creates an author
     * @param xmlObject XML representation of AuthorDto
     * @return ResponseDto
     */
    @Override
    public String createAuthor(String xmlObject) {
        String retValue = null;

        try {
            AuthorDto authorDto = xmlMapper.readValue(xmlObject, AuthorDto.class);

            // write value to db
            ResponseDto responseDto = authorDao.createAuthor(authorDto);

            if (responseDto.getCode().equalsIgnoreCase("PASS")) {
                retValue = responseDto.getDescription();
            } else {
                retValue = "Failed to create author";
            }
        } catch (JsonProcessingException jpe) {
            jpe.getMessage();
        }
        return retValue;
    }

    @Override
    public AuthorDto updateAuthor(String xmlObject) {
        AuthorDto retValue = null;

        ResponseDto responseDto = new ResponseDto();

        try {
            AuthorDto authorDto = xmlMapper.readValue(xmlObject, AuthorDto.class);

            // get all records from db
            List<AuthorDto> authorDtoList = getAuthorList();

            // compare each record with what is being passed by the user
            for (AuthorDto updatedAuthor : authorDtoList) {
                if (!authorDto.equals(updatedAuthor)) {
                    updatedAuthor.setAuthor(authorDto.getAuthor());
                }
                responseDto = authorDao.updateAuthor(updatedAuthor);
                if (responseDto.getCode().equalsIgnoreCase("PASS")) {
                    retValue = updatedAuthor;
                }
            }
        } catch(IOException ioe) {
            ioe.getMessage();
        }
        return retValue;
    }

    @Override
    public void deleteAuthorById(int id) {
        authorDao.deleteAuthorById(id);
    }

    @Override
    public Author findById(int id) {
        return authorDao.findById(id);
    }

    @Override
    public List<AuthorDto> getAuthorList() {
        List<AuthorDto> authorDtoList = new ArrayList<>();
        AuthorDto authorDto;

        try {
            List<Author> authorList = authorDao.findAllAuthors();
            for (Author author : authorList) {
                authorDto = beanMapper.map(author, AuthorDto.class);
                authorDtoList.add(authorDto);
            }
        } catch (Exception e) {
            e.getMessage();
        }
        return authorDtoList;
    }
}
