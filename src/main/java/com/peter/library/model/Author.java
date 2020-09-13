package com.peter.library.model;

import javax.persistence.*;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "AUTHOR")
@XmlRootElement(name="author")
@XmlAccessorType(XmlAccessType.FIELD)
public class Author {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name="ID", updatable = false, nullable = false)
    @XmlElement
    private int id;

    @Column(name="AUTHOR")
    @XmlElement
    private String author;

    @ManyToMany(mappedBy = "author")
    private List<Book> bookList = new ArrayList<>();

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getAuthor() {
        return author;
    }

    public void setAuthor(String author) {
        this.author = author;
    }

    public List<Book> getBookList() {
        return bookList;
    }

    public void setBookList(List<Book> bookList) {
        this.bookList = bookList;
    }
}
