package com.bookscatalog.domain;

import org.hibernate.validator.constraints.Length;
import org.hibernate.validator.constraints.NotEmpty;

import javax.persistence.*;
import java.util.*;

@Entity
@Table(name = "Author")
public class Author {
    @Id
    @Column(name = "id")
    @GeneratedValue
    private int id;

    public String getFullName() {
        return getFirstname() + " " + getLastname();
    }


    @NotEmpty
    @Length(max = 45)
    @Column(name = "firstname")
    private String firstname;

    @NotEmpty
    @Length(max = 45)
    @Column(name = "lastname")
    private String lastname;


    @ManyToMany(mappedBy = "authors", fetch = FetchType.LAZY)
    private List<Book> books;

    public Author() {
    }

    public Author(String firstname, String lastname) {
        this.firstname = firstname;
        this.lastname = lastname;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getFirstname() {
        return firstname;
    }

    public void setFirstname(String firstname) {
        this.firstname = firstname;
    }

    public String getLastname() {
        return lastname;
    }

    public void setLastname(String lastname) {
        this.lastname = lastname;
    }

    public List<Book> getBooks() {
        return books;
    }

    public void setBooks(List<Book> books) {
        this.books = books;
    }

    @Override
    public String toString() {
        return "Author{" +
                "id=" + id +
                ", firstname='" + firstname + '\'' +
                ", lastname='" + lastname + "'" +
                '}';
    }

}
