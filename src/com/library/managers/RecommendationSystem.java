package com.library.managers;

import com.library.core.Book;
import com.library.core.Patron;

import java.util.List;

public interface RecommendationSystem {
    List<Book> generateRecommendations(Patron patron);
}
