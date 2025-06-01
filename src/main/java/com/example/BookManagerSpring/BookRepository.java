package com.example.BookManagerSpring;

import jakarta.transaction.Transactional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface BookRepository extends JpaRepository<Book, Long> {
    List<Book> findByTitle(String title);
    boolean existsByTitle(String title);
    @Transactional
    void deleteByTitle(String title);

}
