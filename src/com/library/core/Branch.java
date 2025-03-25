package com.library.core;

import java.util.ArrayList;
import java.util.List;

public class Branch {
    private final String id;
    private final String name;
    private final List<Book> books = new ArrayList<>();

    public Branch(String id, String name) {
        this.id = id;
        this.name = name;
    }

    public void addBook(Book book) {
        books.add(book);
        book.setCurrentBranch(this);
    }

    // Getters
    public String getId() { return id; }
    public String getName() { return name; }
    public List<Book> getBooks() { return books; }
}