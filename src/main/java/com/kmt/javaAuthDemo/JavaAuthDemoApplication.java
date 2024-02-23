package com.kmt.javaAuthDemo;

import com.kmt.javaAuthDemo.controller.AuthenticationController;
import com.kmt.javaAuthDemo.controller.BookController;
import com.kmt.javaAuthDemo.model.Book;
import com.kmt.javaAuthDemo.repository.BookRepository;
import com.kmt.javaAuthDemo.repository.SessionRepository;
import com.kmt.javaAuthDemo.repository.UserRepository;
import com.kmt.javaAuthDemo.service.AuthenticationService;
import com.kmt.javaAuthDemo.service.BookService;
import com.kmt.javaAuthDemo.service.SessionService;
import com.kmt.javaAuthDemo.service.UserService;

public class JavaAuthDemoApplication {

    public static void main(String[] args) {
        //REPOSITORIES
        UserRepository userRepository = new UserRepository();
        BookRepository bookRepository = new BookRepository();
        SessionRepository sessionRepository = new SessionRepository();

        //SERVICES
        UserService userService = new UserService(userRepository);
        AuthenticationService authenticationService = new AuthenticationService(userRepository);
        SessionService sessionService = new SessionService(sessionRepository);
        BookService bookService = new BookService(bookRepository);

        //CONTROLLERS
        BookController bookController = new BookController(bookService, sessionService);
        AuthenticationController authenticationController = new AuthenticationController(authenticationService, sessionService);

        System.out.println("Library Management App initializing...\n");
        // Initialize repository with some sample books
        bookRepository.addBook(new Book("The Great Gatsby", "F. Scott Fitzgerald"));
        //Admin user creation
        userService.registerUser("admin", "admin123");


        System.out.println("\nLibrary Management app started...\n");
        // Admin user login
        String adminSessionId = authenticationController.login("admin", "admin123");
        // Admin user uses his session Id to add book
        bookController.addBook(adminSessionId, new Book("To Kill a Mockingbird", "Harper Lee"));
        System.out.println("");
        bookController.addBook(adminSessionId, new Book("1984", "George Orwell"));
    }
}
