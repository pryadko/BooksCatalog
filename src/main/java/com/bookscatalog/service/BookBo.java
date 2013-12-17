package com.bookscatalog.service;

import com.bookscatalog.domain.Book;

import java.util.List;

public interface BookBo {
    public void save(Book book);

    public void update(Book book);

    public void delete(Book book);

    public List<Book> getAllBooks();

    public Book findBookById(int id);

    public List<Book> findBooksByName(String name);

    public List<Book> getBooksByAuthor(int authorId);
}
