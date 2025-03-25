package com.library.core;

import java.util.ArrayList;
import java.util.List;

public class Patron {
    private final String email;
    private final String name;
    private final List<Book> borrowedBooks = new ArrayList<>();

    public Patron(String email, String name) {
        this.email = email;
        this.name = name;
    }

    // Getters
    public String getEmail() { return email; }
    public String getName() { return name; }
    public List<Book> getBorrowedBooks() { return borrowedBooks; }
}