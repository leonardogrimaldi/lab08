package it.unibo.deathnote.impl;

import it.unibo.deathnote.api.DeathNote;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.List;
import java.util.Set;

public class DeathNoteImpl implements DeathNote {

    private Set<String> names;

    @Override
    public String getRule(int ruleNumber) {
        // TODO Auto-generated method stub
        throw new IllegalArgumentException("Cannot access rule number 0 and negative rules as they do not exist");
    }

    @Override
    public void writeName(String name) {
        // TODO Auto-generated method stub
        throw new IllegalArgumentException("Cannot insert the same person");
    }

    @Override
    public boolean writeDeathCause(String cause) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'writeDeathCause'");
    }

    @Override
    public boolean writeDetails(String details) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'writeDetails'");
    }

    @Override
    public String getDeathCause(String name) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'getDeathCause'");
    }

    @Override
    public String getDeathDetails(String name) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'getDeathDetails'");
    }

    @Override
    public boolean isNameWritten(String name) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'isNameWritten'");
    }
    
}
