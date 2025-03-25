package com.library.managers;

import com.library.core.Patron;
import java.util.List;

public interface PatronManager {
    void addPatron(Patron patron);
    void removePatron(Patron patron);
    Patron findPatronByEmail(String email);
    List<Patron> getAllPatrons();
    void updatePatronInfo(Patron patron);
}