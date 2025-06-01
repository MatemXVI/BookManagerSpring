package com.example.BookManagerSpring;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class BookService {

    private final BookRepository bookRepository;

    @Autowired
    public BookService(BookRepository bookRepository) {
        this.bookRepository = bookRepository;
    }

    public List<Book> selectAll() {
        return bookRepository.findAll();
    }

    public List<Book> selectByTitle(String title){
        return bookRepository.findByTitle(title);
    }

    public void save(Book book){
        bookRepository.save(book);
    }
    public boolean existsByTitle(String title) {
        return bookRepository.existsByTitle(title);
    }

    public boolean delete(String title){
        if(bookRepository.existsByTitle(title)){
            bookRepository.deleteByTitle(title);
            return true;
        }else{
            return false;
        }
    }
}

