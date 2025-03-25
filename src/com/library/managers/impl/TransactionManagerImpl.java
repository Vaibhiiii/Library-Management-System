package com.library.managers.impl;

import com.library.core.*;
import com.library.managers.TransactionManager;

import java.time.LocalDate;
import java.util.*;
import java.util.concurrent.ConcurrentHashMap;

public class TransactionManagerImpl implements TransactionManager {
    private final Map<String, Transaction> activeTransactions = new ConcurrentHashMap<>();
    private final List<Transaction> transactionHistory = new ArrayList<>();
    private static final double DAILY_FINE_RATE = 0.50;
    private static final int LOAN_PERIOD_DAYS = 14;

    @Override
    public Transaction checkoutBook(Patron patron, Book book) {
        if (!book.isAvailable()) {
            throw new IllegalStateException("Book is not available for checkout");
        }

        String transactionId = UUID.randomUUID().toString();
        Transaction transaction = new Transaction(
                transactionId,
                book,
                patron
        );

        book.setAvailable(false);
        patron.getBorrowedBooks().add(book);
        activeTransactions.put(book.getIsbn(), transaction);

        return transaction;
    }

    @Override
    public Transaction returnBook(Book book) {
        Transaction transaction = activeTransactions.remove(book.getIsbn());
        if (transaction == null) {
            throw new IllegalStateException("No active transaction for this book");
        }

        transaction.setReturnDate(LocalDate.now());
        transaction.calculateFine();
        book.setAvailable(true);
        transaction.getPatron().getBorrowedBooks().remove(book);
        transactionHistory.add(transaction);

        return transaction;
    }

    @Override
    public List<Transaction> getTransactionHistory() {
        return Collections.unmodifiableList(transactionHistory);
    }

    @Override
    public double calculateFine(Transaction transaction) {
        transaction.calculateFine();
        return transaction.getFine();
    }

    @Override
    public List<Transaction> getActiveTransactions() {
        return new ArrayList<>(activeTransactions.values());
    }
}