package com.kmt.javaAuthDemo.controller;

import com.kmt.javaAuthDemo.model.Book;
import com.kmt.javaAuthDemo.service.AuthenticationService;
import com.kmt.javaAuthDemo.service.BookService;

import java.util.Collections;
import java.util.List;

public class BookController {
    private final BookService bookService;
    private final AuthenticationService authenticationService;

    public BookController(BookService bookService, AuthenticationService authenticationService) {
        this.bookService = bookService;
        this.authenticationService = authenticationService;
    }

    public void addBook(String username, String password, Book book) {
        System.out.println("Received request to add book by user: " + username);
        if (authenticationService.authenticate(username, password)) {
            bookService.addBook(book);
        } else {
            System.out.println("Authentication failed. Book not added.");
        }
    }

    public List<Book> getAllBooks(String username, String password) {
        System.out.println("Received request to get All book by user: " + username);
        if (authenticationService.authenticate(username, password)) {
            return bookService.getAllBooks();
        } else {
            System.out.println("Authentication failed. Get all books failed.");
        }
        return Collections.emptyList();
    }

    public void updateBook(String username, String password, Book book) {
        System.out.println("Received request to update book by user: " + username);
        if (authenticationService.authenticate(username, password)) {
            bookService.updateBook(book);
        } else {
            System.out.println("Authentication failed. Book not updated.");
        }
    }

    public void deleteBook(String username, String password, int id) {
        System.out.println("Received request to delete book by user: " + username);
        if (authenticationService.authenticate(username, password)) {
            bookService.deleteBook(id);
        } else {
            System.out.println("Authentication failed. Book not deleted.");
        }
    }
}
