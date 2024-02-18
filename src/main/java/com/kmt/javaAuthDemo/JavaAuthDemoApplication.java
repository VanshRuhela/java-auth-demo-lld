package com.kmt.javaAuthDemo;

import com.kmt.javaAuthDemo.controller.BookController;
import com.kmt.javaAuthDemo.model.Book;
import com.kmt.javaAuthDemo.repository.BookRepository;
import com.kmt.javaAuthDemo.repository.UserRepository;
import com.kmt.javaAuthDemo.service.AuthenticationService;
import com.kmt.javaAuthDemo.service.BookService;
import com.kmt.javaAuthDemo.service.UserService;

public class JavaAuthDemoApplication {

    public static void main(String[] args) {
        UserRepository userRepository = new UserRepository();
        UserService userService = new UserService(userRepository);
        AuthenticationService authenticationService = new AuthenticationService(userRepository);
        BookRepository bookRepository = new BookRepository();
        BookService bookService = new BookService(bookRepository);
        BookController bookController = new BookController(bookService, authenticationService);

        System.out.println("Library Management App initializing...\n");

        // Initialize repository with some sample books
        bookRepository.addBook(new Book("The Great Gatsby", "F. Scott Fitzgerald"));


        //Admin user creation
        userService.registerUser("admin", "admin123");

        System.out.println("\nLibrary Management app started...\n");

        // Admin user authentication and book addition
        bookController.addBook("admin", "admin123", new Book("To Kill a Mockingbird", "Harper Lee"));
        System.out.println("");
        bookController.addBook("admin", "admin123", new Book("1984", "George Orwell"));
    }
}
