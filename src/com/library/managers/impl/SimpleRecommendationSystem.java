package com.library.managers.impl;

import com.library.core.*;
import com.library.managers.BookManager;
import com.library.managers.RecommendationSystem;

import java.util.*;

public class SimpleRecommendationSystem implements RecommendationSystem {
    private final BookManager bookManager;

    public SimpleRecommendationSystem(BookManager bookManager) {
        this.bookManager = bookManager;
    }

    @Override
    public List<Book> generateRecommendations(Patron patron) {
        // Get all books from the manager instead of static method
        List<Book> allBooks = new ArrayList<>(
                ((BookManagerImpl)bookManager).getAllBooks()
        );

        Set<String> favoriteAuthors = new HashSet<>();
        for(Book b : patron.getBorrowedBooks()) {
            favoriteAuthors.add(b.getAuthor().toLowerCase());
        }

        List<Book> recommendations = new ArrayList<>();
        for(Book b : allBooks) {
            if(favoriteAuthors.contains(b.getAuthor().toLowerCase()) &&
                    !patron.getBorrowedBooks().contains(b)) {
                recommendations.add(b);
            }
        }
        return recommendations;
    }
}