package com.kmt.javaAuthDemo;

import com.kmt.javaAuthDemo.controller.AuthenticationController;
import com.kmt.javaAuthDemo.controller.BookController;
import com.kmt.javaAuthDemo.controller.UserController;
import com.kmt.javaAuthDemo.model.Book;
import com.kmt.javaAuthDemo.model.Role;
import com.kmt.javaAuthDemo.model.User;
import com.kmt.javaAuthDemo.repository.BookRepository;
import com.kmt.javaAuthDemo.repository.UserRepository;
import com.kmt.javaAuthDemo.service.AuthenticationService;
import com.kmt.javaAuthDemo.service.BookService;
import com.kmt.javaAuthDemo.service.UserService;
import com.kmt.javaAuthDemo.utils.security.PasswordHashingUtil;

public class JavaAuthDemoApplication {

    public static void main(String[] args) {
        System.out.println("Library Management App initializing...\n");

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
        UserController userController = new UserController(userService);

        // Initialize repository with some sample books
        bookRepository.addBook(new Book("The Great Gatsby", "F. Scott Fitzgerald"));
        // Initialize with a default admin user
        userService.registerAdmin("admin", "admin123");
        System.out.println("Default admin user created.");

        System.out.println("\nLibrary Management app started...\n");

        // Admin user login
        String adminToken = authenticationController.login("admin", "admin123");
        System.out.println("");
        // Register Librarian
        userController.registerLibrarian(adminToken,"librarian", "lib123");
        System.out.println("");


        // Librarian user login
        String librarianToken = authenticationController.login("librarian", "lib123");
        System.out.println("");
        // Add book
        bookController.addBook(librarianToken, new Book("To Kill a Mockingbird", "Harper Lee"));
        System.out.println("");
        // Add book
        bookController.addBook(librarianToken, new Book("1984", "George Orwell"));
        System.out.println("");
        // Register customer
        userController.registerCustomer(librarianToken, "customer", "cust123");
        System.out.println("");

        // Customer login
        String customerToken = authenticationController.login("customer", "cust123");
        System.out.println("");
        // Get all books // Authorized action for customer
        bookController.getAllBooks(customerToken);
        System.out.println("");
        // Add book. // Unauthorized action for customer
        bookController.addBook(customerToken, new Book("The Hobbit", "J.R.R. Tolkien"));
        System.out.println("");
    }
}
