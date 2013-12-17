package com.bookscatalog.dao;

import com.bookscatalog.domain.Author;

import java.util.List;

public interface AuthorDAO {
    public void save(Author author);

    public void update(Author author);

    public List<Author> getAllAuthors();

    public void delete(Author author);

    public Author findAuthorById(int id);


}
