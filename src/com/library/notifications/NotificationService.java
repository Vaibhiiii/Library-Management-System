package com.library.notifications;

import com.library.core.Patron;
import com.library.core.Book;

public interface NotificationService {
    void notifyAvailable(Patron patron, Book book);
}