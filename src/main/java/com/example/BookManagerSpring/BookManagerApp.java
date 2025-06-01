package com.example.BookManagerSpring;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Scanner;

@Component
public class BookManagerApp {

    private final Scanner scanner = new Scanner(System.in);
    private final BookService bookService;

    @Autowired
    public BookManagerApp(BookService bookService) {
        this.bookService = bookService;
    }


    public void separation(){
        System.out.println("-----------------------------");
    }

    public void info(){
        final String TITLEAPP = "System zarządzania biblioteką";
        final String VERSION = "0.1.1";
        final String AUTHOR = "Mateusz Milczarek";
        System.out.println(TITLEAPP);
        System.out.println("Wersja: " + VERSION + ", " + "Autor: " + AUTHOR);
    }
    public void menu(){
        System.out.println("1. Dodaj książkę do biblioteki");
        System.out.println("2. Wyświetl listę wszystkich książek");
        System.out.println("3. Wyszukaj książkę po tytule");
        System.out.println("4. Usuń książkę po tytule");
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

    //1.
    public void addBook(){
        String title = title();
        String author = author();
        int year = year();
        if (!bookService.existsByTitle(title)) {
                bookService.save(new Book(title, author, year));
                System.out.println("Dodano książkę do biblioteki.");
        }else{
                System.out.println("Taka książka już istnieje.");
        }
        separation();
    }
    //2.
    public void findAllBooks(){
        List<Book> books =  bookService.selectAll();
        if(books != null){
            int i = 0;
            for(Book book : books){
                i++;
                System.out.println(i + ". " + book.getTitle() + ", " + book.getAuthor() + ", " + book.getYear());
            }
        }else{
            System.out.println("Nie znaleziono żadnej książki");
        }
        separation();
    }
    //3.
    public void findBookByTitle(){
        String title = title();
        List<Book> books =  bookService.selectByTitle(title);
        if(!books.isEmpty()){
            for(Book book : books) {
                System.out.println(book.getTitle() + ", " + book.getAuthor() + ", " + book.getYear());
            }
        }
        else
            System.out.println("Nie znaleziono książki.");
        separation();
    }
    //4.
    public void removeBook(){
        String title = title();
        if(bookService.delete(title))
            System.out.println("Książka usunięta pomyślnie");
        else
            System.out.println("Brak podanej książki do usunięcia");
        separation();
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
