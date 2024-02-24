package com.kmt.javaAuthDemo;

import com.kmt.javaAuthDemo.controller.AuthenticationController;
import com.kmt.javaAuthDemo.controller.BookController;
import com.kmt.javaAuthDemo.model.Book;
import com.kmt.javaAuthDemo.repository.BookRepository;
import com.kmt.javaAuthDemo.repository.UserRepository;
import com.kmt.javaAuthDemo.service.AuthenticationService;
import com.kmt.javaAuthDemo.service.BookService;
import com.kmt.javaAuthDemo.service.UserService;

public class JavaAuthDemoApplication {

    public static void main(String[] args) {
//REPOSITORIES
        UserRepository userRepository = new UserRepository();
        BookRepository bookRepository = new BookRepository();

        //SERVICES
        UserService userService = new UserService(userRepository);
        AuthenticationService authenticationService = new AuthenticationService(userRepository);
        BookService bookService = new BookService(bookRepository);

        //CONTROLLERS
        BookController bookController = new BookController(bookService);
        AuthenticationController authenticationController = new AuthenticationController(authenticationService);

        System.out.println("Library Management App initializing...\n");
        // Initialize repository with some sample books
        bookRepository.addBook(new Book("The Great Gatsby", "F. Scott Fitzgerald"));
        //Admin user creation
        userService.registerUser("admin", "admin123");


        System.out.println("\nLibrary Management app started...\n");
        // Admin user login
        String adminToken = authenticationController.login("admin", "admin123");
        System.out.println("");
        bookController.addBook(adminToken, new Book("To Kill a Mockingbird", "Harper Lee"));
        System.out.println("");
        bookController.addBook(adminToken, new Book("1984", "George Orwell"));
    }
}
