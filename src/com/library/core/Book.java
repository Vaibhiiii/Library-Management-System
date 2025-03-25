package com.library.core;

public class Book {
    private final String isbn;
    private final String title;
    private final String author;
    private boolean available;
    private Branch currentBranch;

    public Book(String isbn, String title, String author, int i) {
        this.isbn = isbn;
        this.title = title;
        this.author = author;
        this.available = true;
    }

    // Getters and setters
    public String getIsbn() { return isbn; }
    public String getTitle() { return title; }
    public String getAuthor() { return author; }
    public boolean isAvailable() { return available; }
    public void setAvailable(boolean available) { this.available = available; }
    public Branch getCurrentBranch() { return currentBranch; }
    public void setCurrentBranch(Branch branch) { this.currentBranch = branch; }
}