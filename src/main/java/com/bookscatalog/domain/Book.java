package com.bookscatalog.domain;

import org.hibernate.validator.constraints.Length;
import org.hibernate.validator.constraints.NotEmpty;
import org.hibernate.validator.constraints.Range;


import javax.persistence.*;
import java.util.*;

@Entity
@Table(name = "Book")
public class Book {
    @Id
    @Column(name = "id")
    @GeneratedValue
    private int id;

    @NotEmpty
    @Length(max = 100)
    @Column(name = "name")
    private String name;

    @NotEmpty
    @Length(max = 300)
    @Column(name = "shortDescription")
    private String shortDescription;

    @Range(min = 1000, max = 2100)
    @Column(name = "year", nullable = false)
    private int year;

    @NotEmpty
    @ManyToMany(fetch = FetchType.LAZY)
    @JoinTable(name = "AuthorToBook",
            joinColumns = {@JoinColumn(name = "bookId")},
            inverseJoinColumns = {@JoinColumn(name = "authorId")})
    private List<Author> authors;

    public Book() {
    }

    public Book(String name, String shortDescription, int year) {
        this.name = name;
        this.shortDescription = shortDescription;
        this.year = year;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getShortDescription() {
        return shortDescription;
    }

    public void setShortDescription(String shortDescription) {
        this.shortDescription = shortDescription;
    }

    public int getYear() {
        return year;
    }

    public void setYear(int year) {
        this.year = year;
    }

    public List<Author> getAuthors() {
        return authors;
    }

    public void setAuthors(List<Author> authors) {
        this.authors = authors;
    }

    @Override
    public String toString() {
        return "Book{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", shortDescription='" + shortDescription + '\'' +
                ", year=" + year + "'" +
                '}';
    }

}
