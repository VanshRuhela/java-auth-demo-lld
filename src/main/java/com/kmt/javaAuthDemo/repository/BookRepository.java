package com.kmt.javaAuthDemo.repository;

import com.kmt.javaAuthDemo.model.Book;

import java.util.*;

public class BookRepository {
    private final Map<Integer, Book> books = new HashMap<>();
    private int idCounter = 0;

    public int addBook(Book book) {
        int id = ++idCounter;
        book.setId(id);
        books.put(id, book);
        System.out.println("Book added: " + book);
        return id;
    }

    public Book getBookById(int id) {
        Book book = books.get(id);
        if (book != null) {
            System.out.println("Retrieved book by ID: " + id + " - " + book.getTitle() + " by " + book.getAuthor());
        } else {
            System.out.println("No book found with ID: " + id);
        }
        return book;
    }

    public List<Book> getAllBooks() {
        System.out.println("Retrieving all books. Total books: " + books.size());
        return new ArrayList<>(books.values());
    }

    public void updateBook(Book book) {
        books.put(book.getId(), book);
        System.out.println("Book updated: " + book);
    }

    public void deleteBook(int id) {
        books.remove(id);
        System.out.println("Book deleted with ID: " + id);
    }
}

