package com.library.core;

import java.time.LocalDate;
import java.time.temporal.ChronoUnit;

public class Transaction {
    private final String transactionId;
    private final Book book;

    public String getTransactionId() {
        return transactionId;
    }

    public Book getBook() {
        return book;
    }

    public LocalDate getCheckoutDate() {
        return checkoutDate;
    }

    public LocalDate getDueDate() {
        return dueDate;
    }

    public LocalDate getReturnDate() {
        return returnDate;
    }

    private final Patron patron;
    private final LocalDate checkoutDate;
    private LocalDate dueDate;
    private LocalDate returnDate;
    private double fine;

    public Transaction(String transactionId, Book book, Patron patron) {
        this.transactionId = transactionId;
        this.book = book;
        this.patron = patron;
        this.checkoutDate = LocalDate.now();
        this.dueDate = checkoutDate.plusDays(14);
    }

    public void calculateFine() {
        if (returnDate != null && returnDate.isAfter(dueDate)) {
            long daysOverdue = ChronoUnit.DAYS.between(dueDate, returnDate);
            fine = daysOverdue * 0.50; // $0.50/day fine
        }
    }

    // Getters and setters
    public void setReturnDate(LocalDate returnDate) { this.returnDate = returnDate; }
    public double getFine() { return fine; }
    public Patron getPatron() {
        return patron;
    }
}