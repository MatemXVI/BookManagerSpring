package com.example.BookManagerSpring;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.sql.*;

@Component
public class BookController {

    private final BookRepository bookRepository;
    private final Library library;

    @Autowired
    public BookController(BookRepository bookRepository, Library library) {
        this.bookRepository = bookRepository;
        this.library = library;
    }

    public void refresh() {
        library.clear();
        library.setBooks(bookRepository.findAll());
    }

    public void save(Book book){
        bookRepository.save(book);
    }

    public void selectByTitle(String title){
        bookRepository.findByTitle(title);
        }


    public void delete(String title){
        bookRepository.deleteByTitle(title);
    }
}

