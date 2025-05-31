package com.example.BookManagerSpring;

import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
public class Library {
    private List<Book> books = new ArrayList<>();

    public boolean setBook(Long id, String title, String author, int year){
        return books.add(new Book(id, title, author, year));
    }
    public void setBooks(List<Book> books){
        this.books = books;
    }
    public List<Book> getBooks(){
        return new ArrayList<>(books);
    }
    public void clear(){
        books.clear();
    }
    public Book findBookByTitle(String title){
        for(Book book : books){
            if(book.getTitle().equalsIgnoreCase(title))
                return book;
        }
        return null;
    }
    public List<Book> findBooksByAuthor(String author) {
        List<Book> result = new ArrayList<>();
        for (Book book : books) {
            if (book.getAuthor().equalsIgnoreCase(author)) {
                result.add(book);
            }
        }
        return result;
    }
    public boolean removeBook(String title){
        for(int i =0; i < books.size(); i++){
            if(books.get(i).getTitle().equals(title)){
                books.remove(i);
                return true;
            }
        }
        return false;
    }
}

