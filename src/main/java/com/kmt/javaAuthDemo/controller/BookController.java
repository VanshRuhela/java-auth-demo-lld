package com.kmt.javaAuthDemo.controller;

import com.kmt.javaAuthDemo.model.Book;
import com.kmt.javaAuthDemo.repository.SessionRepository;
import com.kmt.javaAuthDemo.service.AuthenticationService;
import com.kmt.javaAuthDemo.service.BookService;
import com.kmt.javaAuthDemo.service.SessionService;

import java.util.Collections;
import java.util.List;

public class BookController {
    private final BookService bookService;
    private final SessionService sessionService;

    public BookController(BookService bookService, SessionService sessionService) {
        this.bookService = bookService;
        this.sessionService = sessionService;
    }

    public void addBook(String sessionId, Book book) {
        System.out.println("Received request to add book.");
        if (sessionService.isValidSession(sessionId)) {
            bookService.addBook(book);
        } else {
            System.out.println("Authentication failed. Book not added.");
        }
    }

    public List<Book> getAllBooks(String sessionId) {
        System.out.println("Received request to get All book.");
        if (sessionService.isValidSession(sessionId)) {
            return bookService.getAllBooks();
        } else {
            System.out.println("Authentication failed. Get all books failed.");
        }
        return Collections.emptyList();
    }

    public void updateBook(String sessionId, Book book) {
        System.out.println("Received request to update book.");
        if (sessionService.isValidSession(sessionId)) {
            bookService.updateBook(book);
        } else {
            System.out.println("Authentication failed. Book not updated.");
        }
    }

    public void deleteBook(String sessionId, int id) {
        System.out.println("Received request to delete book.");
        if (sessionService.isValidSession(sessionId)) {
            bookService.deleteBook(id);
        } else {
            System.out.println("Authentication failed. Book not deleted.");
        }
    }
}
