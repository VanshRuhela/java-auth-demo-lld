package com.kmt.javaAuthDemo.controller;

import com.kmt.javaAuthDemo.model.Book;
import com.kmt.javaAuthDemo.model.Role;
import com.kmt.javaAuthDemo.service.AuthenticationService;
import com.kmt.javaAuthDemo.service.BookService;
import com.kmt.javaAuthDemo.utils.security.TokenUtil;

import java.util.Collections;
import java.util.List;

public class BookController {
    private final BookService bookService;

    public BookController(BookService bookService) {
        this.bookService = bookService;
    }

    public void addBook(String token, Book book) {
        System.out.println("Received request to add book");
        if (TokenUtil.validateToken(token)) {
            Role role = TokenUtil.getRoleFromToken(token);
            if (role == Role.ADMIN || role == Role.LIBRARIAN) {
                System.out.println("Processing request to add book by User: " + TokenUtil.getUsernameFromToken(token) + ", Role: " + role.name() );
                bookService.addBook(book);
                System.out.println("Book added successfully");
            } else {
                System.out.println("Unauthorized: Insufficient permissions. Book not added.");
            }
        } else {
            System.out.println("Authentication failed. Book not added.");
        }
    }

    public List<Book> getAllBooks(String token) {
        System.out.println("Received request to get All book.");
        if (TokenUtil.validateToken(token)) {
            System.out.println("Processing request to get all books by User: " + TokenUtil.getUsernameFromToken(token));
            return bookService.getAllBooks();
        } else {
            System.out.println("Authentication failed. Get all books failed.");
        }
        return Collections.emptyList();
    }

    public void updateBook(String token, Book book) {
        System.out.println("Received request to update book");
        if (TokenUtil.validateToken(token)) {
            Role role = TokenUtil.getRoleFromToken(token);
            if (role == Role.ADMIN || role == Role.LIBRARIAN) {
                System.out.println("Processing request to update book by User: " + TokenUtil.getUsernameFromToken(token) + ", Role: " + role.name() );
                bookService.updateBook(book);
                System.out.println("Book updated successfully");
            } else {
                System.out.println("Unauthorized: Insufficient permissions. Book not added.");
            }
        } else {
            System.out.println("Authentication failed. Book not updated.");
        }
    }

    public void deleteBook(String token, int id) {
        System.out.println("Received request to delete book");
        if (TokenUtil.validateToken(token)) {
            Role role = TokenUtil.getRoleFromToken(token);
            if (role == Role.ADMIN || role == Role.LIBRARIAN) {
                System.out.println("Processing request to delete book by User: " + TokenUtil.getUsernameFromToken(token) + ", Role: " + role.name() );
                bookService.deleteBook(id);
                System.out.println("Book deleted successfully");
            } else {
                System.out.println("Unauthorized: Insufficient permissions. Book not deleted.");
            }
        } else {
            System.out.println("Authentication failed. Book not deleted.");
        }
    }
}
