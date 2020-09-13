package com.peter.library.dto;

public class AuthorDto extends BaseDto {
    public AuthorDto() {
    }

    public AuthorDto(String author) {
        this.author = author;
    }

    private String author;

    public String getAuthor() {
        return author;
    }

    public void setAuthor(String author) {
        this.author = author;
    }
}
