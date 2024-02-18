package com.kmt.javaAuthDemo.service;

import com.kmt.javaAuthDemo.model.Book;
import com.kmt.javaAuthDemo.repository.BookRepository;

import java.util.List;

public class BookService {
    private final BookRepository bookRepository;

    public BookService(BookRepository bookRepository) {
        this.bookRepository = bookRepository;
    }

    public List<Book> getAllBooks() {
        return bookRepository.getAllBooks();
    }

    public void addBook(Book book) {
        bookRepository.addBook(book);
    }

    public void updateBook(Book book) {
        bookRepository.updateBook(book);
    }


    public void deleteBook(int id) {
        bookRepository.deleteBook(id);
    }
}
