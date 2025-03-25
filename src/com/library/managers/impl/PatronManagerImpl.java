package com.library.managers.impl;

import com.library.core.Patron;
import com.library.managers.PatronManager;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class PatronManagerImpl implements PatronManager {
    private final Map<String, Patron> patronsByEmail = new HashMap<>();

    @Override
    public void addPatron(Patron patron) {
        if (patronsByEmail.containsKey(patron.getEmail())) {
            throw new IllegalArgumentException("Patron with this email already exists");
        }
        patronsByEmail.put(patron.getEmail(), patron);
    }

    @Override
    public void removePatron(Patron patron) {
        patronsByEmail.remove(patron.getEmail());
    }

    @Override
    public Patron findPatronByEmail(String email) {
        return patronsByEmail.get(email);
    }

    @Override
    public List<Patron> getAllPatrons() {
        return new ArrayList<>(patronsByEmail.values());
    }

    @Override
    public void updatePatronInfo(Patron patron) {
        if (!patronsByEmail.containsKey(patron.getEmail())) {
            throw new IllegalArgumentException("Patron not found");
        }
        patronsByEmail.put(patron.getEmail(), patron);
    }
}