package com.library.managers;

import com.library.core.Book;
import com.library.core.Branch;
import java.util.List;

public interface BookManager {
    void addBook(Book book);
    void removeBook(Book book);
    Book findBookByIsbn(String isbn);
    List<Book> searchByTitle(String title);
    List<Book> searchByAuthor(String author);
    void transferBook(Book book, Branch fromBranch, Branch toBranch);
    List<Book> getAllBooks();
}