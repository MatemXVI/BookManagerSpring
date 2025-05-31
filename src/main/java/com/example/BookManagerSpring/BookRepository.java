package com.example.BookManagerSpring;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;


public interface BookRepository extends JpaRepository<Book, Long> {
    List<Book> findByTitle(String title);
    void deleteByTitle(String title);

}
