package com.library.core;

import java.time.LocalDate;

public class Reservation {
    private final Patron patron;
    private final Book book;
    private final LocalDate reservationDate;

    public Reservation(Patron patron, Book book) {
        this.patron = patron;
        this.book = book;
        this.reservationDate = LocalDate.now();
    }

    // Getters
    public Patron getPatron() { return patron; }
    public Book getBook() { return book; }
    public LocalDate getReservationDate() { return reservationDate; }
}