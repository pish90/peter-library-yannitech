package com.peter.library.model;

import javax.persistence.*;
import javax.xml.bind.annotation.*;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "BOOK")
@XmlRootElement(name="book")
@XmlAccessorType(XmlAccessType.FIELD)
public class Book {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name="ID", updatable = false, nullable = false)
    @XmlElement
    private int id;

    @Column(name="CATEGORY")
    @XmlAttribute
    private String category;

    @Column(name="LANG")
    @XmlAttribute
    private String lang;

    @Column(name="TITLE")
    @XmlElement
    private String title;

    @Column(name="YEAR")
    @XmlElement
    private String year;

    @Column(name="PRICE")
    @XmlElement
    private String price;

    @ManyToMany(cascade = { CascadeType.ALL })
    @JoinTable(
            name = "BOOK_AUTHOR",
            joinColumns = { @JoinColumn(name = "book_id")},
            inverseJoinColumns = { @JoinColumn(name = "author_id")}
            )
    private List<Author> author = new ArrayList<>();

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getLang() {
        return lang;
    }

    public void setLang(String lang) {
        this.lang = lang;
    }

    public String getYear() {
        return year;
    }

    public void setYear(String year) {
        this.year = year;
    }

    public String getPrice() {
        return price;
    }

    public void setPrice(String price) {
        this.price = price;
    }

    public List<Author> getAuthor() {
        return author;
    }

    public void setAuthor(List<Author> author) {
        this.author = author;
    }
}
