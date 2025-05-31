package com.example.BookManagerSpring;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class BookManagerSpringApplication implements CommandLineRunner {

	private final BookManagerApp app;

	@Autowired
	public BookManagerSpringApplication(BookManagerApp app) {
		this.app = app;
	}

	public static void main(String[] args) {
		SpringApplication.run(BookManagerSpringApplication.class, args);
	}

	@Override
	public void run(String... args) {
		app.info();
		boolean isRunning = true;
		while (isRunning) {
			app.refresh();
			app.menu();
			int option = app.option();
			switch (option) {
				case 1 -> app.addBook();
				case 2 -> app.findAllBooks();
				case 3 -> app.findBookByTitle();
				case 4 -> app.removeBook();
				case 5 -> app.refresh();
				case 0 -> {
					if (app.quit()) isRunning = false;
				}
				default -> System.out.println("Podano niepoprawną wartość.");
			}
		}
	}
}

