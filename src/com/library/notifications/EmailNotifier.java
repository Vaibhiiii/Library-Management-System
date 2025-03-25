package com.library.notifications;

import com.library.core.Patron;
import com.library.core.Book;

public class EmailNotifier implements NotificationService {
    @Override
    public void notifyAvailable(Patron patron, Book book) {
        System.out.printf("Notification sent to %s: Book '%s' is available at %s branch\n",
                patron.getEmail(),
                book.getTitle(),
                book.getCurrentBranch().getName());
    }
}