package com.bookscatalog.service;

import com.bookscatalog.domain.Author;

import java.util.List;

public interface AuthorBo {
    public void save(Author author);

    public void update(Author author);

    public void delete(Author author);

    public List<Author> getAllAuthors();

    public Author findAuthorById(int id);


}
