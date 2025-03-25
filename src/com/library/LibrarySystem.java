package com.library;

import com.library.core.*;
import com.library.managers.*;
import com.library.notifications.*;
import java.util.*;

public class LibrarySystem {
    private final BookManager bookManager;
    private final PatronManager patronManager;
    private final TransactionManager transactionManager;
    private final NotificationService notifier;
    private final Map<Book, Queue<Reservation>> reservations = new HashMap<>();

    public LibrarySystem(BookManager bookManager,
                         PatronManager patronManager,
                         TransactionManager transactionManager,
                         NotificationService notifier, RecommendationSystem recommendationSystem) {
        this.bookManager = bookManager;
        this.patronManager = patronManager;
        this.transactionManager = transactionManager;
        this.notifier = notifier;
    }

    public void checkoutBook(String patronEmail, String bookIsbn) {
        Patron patron = patronManager.findPatronByEmail(patronEmail);
        Book book = bookManager.findBookByIsbn(bookIsbn);
        transactionManager.checkoutBook(patron, book);
    }

    public void returnBook(String bookIsbn) {
        Book book = bookManager.findBookByIsbn(bookIsbn);
        Transaction transaction = transactionManager.returnBook(book);

        if (reservations.containsKey(book)) {
            Queue<Reservation> queue = reservations.get(book);
            while (!queue.isEmpty()) {
                Reservation reservation = queue.poll();
                notifier.notifyAvailable(reservation.getPatron(), book);
            }
        }
    }

    public void reserveBook(Patron patron, Book book) {
        reservations.computeIfAbsent(book, k -> new LinkedList<>())
                .add(new Reservation(patron, book));
    }
}