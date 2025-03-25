package com.library;

import com.library.core.*;
import com.library.managers.*;
import com.library.managers.impl.*;
import com.library.notifications.*;
import java.time.LocalDate;
import java.util.List;

public class LibraryDriver {
    public static void main(String[] args) {
        // Initialize all components
        BookManager bookManager = new BookManagerImpl();
        PatronManager patronManager = new PatronManagerImpl();
        TransactionManager transactionManager = new TransactionManagerImpl();
        NotificationService notifier = new EmailNotifier();
        RecommendationSystem recommendationSystem = new SimpleRecommendationSystem(bookManager);

        LibrarySystem library = new LibrarySystem(
                bookManager, patronManager, transactionManager,
                notifier, recommendationSystem
        );

        // ===== SETUP TEST DATA =====
        // Create branches
        Branch mainBranch = new Branch("B1", "Main Library");
        Branch northBranch = new Branch("B2", "North Branch");
        Branch southBranch = new Branch("B3", "South Branch");

        // Add books
        Book book1 = new Book("978-0132350884", "Clean Code", "Robert Martin", 2008);
        Book book2 = new Book("978-0201633610", "Design Patterns", "Erich Gamma", 1994);
        Book book3 = new Book("978-0321125217", "Domain-Driven Design", "Eric Evans", 2003);
        Book book4 = new Book("978-1617293290", "Spring in Action", "Craig Walls", 2018);
        Book book5 = new Book("978-1491950357", "Building Microservices", "Sam Newman", 2021);
        Book book6 = new Book("978-0134685991", "Effective Java", "Joshua Bloch", 2017);

        // Assign books to branches
        mainBranch.addBook(book1);
        mainBranch.addBook(book2);
        northBranch.addBook(book3);
        northBranch.addBook(book4);
        southBranch.addBook(book5);
        southBranch.addBook(book6);

        // Add books to system
        bookManager.addBook(book1);
        bookManager.addBook(book2);
        bookManager.addBook(book3);
        bookManager.addBook(book4);
        bookManager.addBook(book5);
        bookManager.addBook(book6);

        // Register patrons
        Patron alice = new Patron("alice@example.com", "Alice Wonder");
        Patron bob = new Patron("bob@example.com", "Bob Builder");
        Patron charlie = new Patron("charlie@example.com", "Charlie Brown");
        Patron diana = new Patron("diana@example.com", "Diana Prince");

        patronManager.addPatron(alice);
        patronManager.addPatron(bob);
        patronManager.addPatron(charlie);
        patronManager.addPatron(diana);

        // ===== PRINT INITIAL STATE =====
        System.out.println("===== LIBRARY SYSTEM INITIALIZED =====");


        // ===== TESTING CHECKOUT/RETURN =====
        System.out.println("\n===== TESTING CHECKOUT/RETURN =====");
        System.out.println("\nAlice checks out 'Clean Code':");
        library.checkoutBook("alice@example.com", "978-0132350884");


        System.out.println("\nBob tries to check out 'Clean Code' (should fail):");
        try {
            library.checkoutBook("bob@example.com", "978-0132350884");
        } catch (IllegalStateException e) {
            System.out.println("Error: " + e.getMessage());
        }

        System.out.println("\nAlice returns 'Clean Code':");
        library.returnBook("978-0132350884");

        // ===== TESTING RESERVATIONS =====
        System.out.println("\n===== TESTING RESERVATIONS =====");
        System.out.println("\nAlice checks out 'Clean Code' again:");
        library.checkoutBook("alice@example.com", "978-0132350884");

        System.out.println("\nBob reserves 'Clean Code':");
        library.reserveBook(bob, book1);

        System.out.println("\nCharlie reserves 'Clean Code':");
        library.reserveBook(charlie, book1);

        System.out.println("\nAlice returns 'Clean Code' (should notify Bob and Charlie):");
        library.returnBook("978-0132350884");

        // ===== TESTING BRANCH TRANSFERS =====
        System.out.println("\n===== TESTING BRANCH TRANSFERS =====");
        System.out.println("\nCurrent location of 'Domain-Driven Design': " +
                book3.getCurrentBranch().getName());

        System.out.println("\nTransferring book to Main Library:");
        bookManager.transferBook(book3, northBranch, mainBranch);
        System.out.println("New location: " + book3.getCurrentBranch().getName());

        // ===== TESTING RECOMMENDATIONS =====
        System.out.println("\n===== TESTING RECOMMENDATIONS =====");
        System.out.println("\nAlice checks out 'Effective Java':");
        library.checkoutBook("alice@example.com", "978-0134685991");


        // ===== TESTING FINE CALCULATION =====
        System.out.println("\n===== TESTING FINE CALCULATION =====");
        System.out.println("\nBob checks out 'Design Patterns':");
        Transaction transaction = transactionManager.checkoutBook(bob, book2);

        // Simulate late return (20 days overdue)
        System.out.println("\nSimulating late return (20 days overdue):");
        transaction.setReturnDate(LocalDate.now().plusDays(20));
        transaction.calculateFine();

        System.out.printf("Due Date: %s | Return Date: %s%n",
                transaction.getDueDate(), transaction.getReturnDate());
        System.out.printf("Calculated fine: $%.2f%n", transaction.getFine());

        // ===== FINAL STATE =====
        System.out.println("\n===== FINAL LIBRARY STATE =====");
    }
}