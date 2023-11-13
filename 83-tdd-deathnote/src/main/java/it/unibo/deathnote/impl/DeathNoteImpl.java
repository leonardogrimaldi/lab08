package it.unibo.deathnote.impl;

import it.unibo.deathnote.api.DeathNote;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.Set;

public class DeathNoteImpl implements DeathNote {

    final private Set<String> names;
    private String lastName;

    class Human {
        final private String name;
        final private String cause;
        final private String details;

        public Human(final String name, final String cause, final String details) {
            this.name = name;
            this.cause = cause;
            this.details = details;
        }
    }

    public DeathNoteImpl() {
        names = new HashSet<String>();
    }

    @Override
    public String getRule(final int ruleNumber) {
        try {
            return DeathNote.RULES.get(ruleNumber);
        } catch (IndexOutOfBoundsException e) {
            throw new IllegalArgumentException("Cannot access rule number 0 and negative rules as they do not exist");
        }
    }

    @Override
    public void writeName(final String name) {
        if (name == null) {
            throw new IllegalArgumentException("Cannot insert null string inside death note");
        }
        if (name.equals("")) {
            throw new IllegalArgumentException("Cannot insert empty string inside death note");
        }
        if (!names.add(name)) {
            throw new IllegalArgumentException("Cannot insert the same person");
        }
    }

    @Override
    public boolean writeDeathCause(final String cause) {
        
    }

    @Override
    public boolean writeDetails(final String details) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'writeDetails'");
    }

    @Override
    public String getDeathCause(final String name) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'getDeathCause'");
    }

    @Override
    public String getDeathDetails(final String name) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'getDeathDetails'");
    }

    @Override
    public boolean isNameWritten(final String name) {
        return names.contains(name);
    }
    
}
