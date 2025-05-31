package com.example.BookManagerSpring;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.Scanner;

@Component
public class BookManagerApp {

    private final Scanner scanner = new Scanner(System.in);
    private final BookController bookController;
    private final Library library;

    @Autowired
    public BookManagerApp(BookController bookController, Library library) {
        this.bookController = bookController;
        this.library = library;
    }


    public void info(){
        String titleApp = "System zarządzania biblioteką";
        String version = "0.0.1";
        String author = "Mateusz Milczarek";
        System.out.println(titleApp);
        System.out.println("Wersja: " + version + ", " + "Autor: " + author);
    }
    public void menu(){
        System.out.println("1. Dodaj książkę do biblioteki");
        System.out.println("2. Wyświetl listę wszystkich książek");
        System.out.println("3. Wyszukaj książkę po tytule");
        System.out.println("4. Usuń książkę po tytule");
        System.out.println("5. Odśwież bazę książek");
        System.out.println("0. Wyjście");
    }
    public int option(){
        System.out.print("Wybierz opcję: ");
        try{
            return Integer.parseInt(scanner.nextLine());
        } catch (NumberFormatException e) {
            return -1;
        }
    }
    public String title(){
        System.out.println("Podaj tytuł książki");
        return scanner.nextLine();
    }
    public String author(){
        System.out.println("Podaj autora książki");
        return scanner.nextLine();
    }
    public int year(){
        System.out.println("Podaj rok");
        try{
            return Integer.parseInt(scanner.nextLine());
        } catch (NumberFormatException e) {
            return -1;
        }
    }

    public void refresh(){
       bookController.refresh();
    }


    //1.
    public void addBook(){
        String title = title();
        String author = author();
        int year = year();
        bookController.save(new Book(title, author, year));
    }
    //2.
    public void findAllBooks(){
        if(!library.getBooks().isEmpty()){
            int i = 0;
            for(Book book : library.getBooks()){
                i++;
                System.out.println(i++ + ". " + book.getTitle() + ", " + book.getAuthor() + ", " + book.getYear());
            }
        }else{
            System.out.println("Lista książek jest pusta");
        }
    }
    //3.
    public void findBookByTitle(){
        String title = title();
        bookController.selectByTitle(title);
        Book book = library.findBookByTitle(title);
        if(book != null)
            System.out.println(book);
        else
            System.out.println("Nie znaleziono książki.");
    }
    //4.
    public void removeBook(){
        String title = title();
        bookController.delete(title);
        if(library.removeBook(title))
            System.out.println("Książka usunięta pomyślnie");
        else
            System.out.println("Brak podanej książki do usunięcia");
    }
    //QUIT
    public boolean quit(){
        System.out.print("Czy chcesz wyjść z aplikacji?(T/N): ");
        String yesOrNo = scanner.nextLine();
        if(yesOrNo.equalsIgnoreCase("t")){
            System.out.println("Trwa zamykanie aplikacji...");
//                        Thread.sleep(250);
            return true;
        }else{
            return false;
        }
    }
}
