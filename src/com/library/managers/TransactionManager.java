package com.library.managers;

import com.library.core.Book;
import com.library.core.Patron;
import com.library.core.Transaction;
import java.util.List;

public interface TransactionManager {
    Transaction checkoutBook(Patron patron, Book book);
    Transaction returnBook(Book book);
    List<Transaction> getTransactionHistory();
    double calculateFine(Transaction transaction);
    List<Transaction> getActiveTransactions();
}