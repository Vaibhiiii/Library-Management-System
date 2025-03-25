package com.library.managers.impl;

import com.library.core.Book;
import com.library.core.Branch;
import com.library.managers.BookManager;

import java.util.*;
import java.util.stream.Collectors;

public class BookManagerImpl implements BookManager {
    private static final Map<String, Book> booksByIsbn = new HashMap<>();
    private final Map<String, List<Book>> booksByTitle = new HashMap<>();
    private final Map<String, List<Book>> booksByAuthor = new HashMap<>();

    @Override
    public void addBook(Book book) {
        booksByIsbn.put(book.getIsbn(), book);

        booksByTitle.computeIfAbsent(book.getTitle().toLowerCase(),
                k -> new ArrayList<>()).add(book);

        booksByAuthor.computeIfAbsent(book.getAuthor().toLowerCase(),
                k -> new ArrayList<>()).add(book);
    }

    @Override
    public void removeBook(Book book) {
        booksByIsbn.remove(book.getIsbn());

        booksByTitle.get(book.getTitle().toLowerCase()).remove(book);
        booksByAuthor.get(book.getAuthor().toLowerCase()).remove(book);
    }

    @Override
    public Book findBookByIsbn(String isbn) {
        return booksByIsbn.get(isbn);
    }

    @Override
    public List<Book> searchByTitle(String title) {
        return booksByTitle.getOrDefault(title.toLowerCase(), Collections.emptyList());
    }

    @Override
    public List<Book> searchByAuthor(String author) {
        return booksByAuthor.getOrDefault(author.toLowerCase(), Collections.emptyList());
    }

    @Override
    public void transferBook(Book book, Branch fromBranch, Branch toBranch) {
        fromBranch.getBooks().remove(book);
        toBranch.addBook(book);
    }

    // New method to get all books
    public List<Book> getAllBooks() {
        return new ArrayList<>(booksByIsbn.values());
    }
}