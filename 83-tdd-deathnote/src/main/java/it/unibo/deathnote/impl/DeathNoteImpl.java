package it.unibo.deathnote.impl;

import it.unibo.deathnote.api.DeathNote;
import it.unibo.deathnote.Human;

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

    final private Set<Human> humans;
    private Human lastHumanWritten;
    private long timeLastHumanWritten;

    public DeathNoteImpl() {
        humans = new HashSet<Human>();
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
            throw new NullPointerException("Cannot insert null string inside death note");
        }
        if (name.equals("")) {
            throw new IllegalArgumentException("Cannot insert empty string inside death note");
        }
        final Human hNew = new Human(name);
        if (!humans.add(hNew)) {
            throw new IllegalArgumentException("Cannot insert the same person");
        }
        lastHumanWritten = hNew;
        timeLastHumanWritten = System.currentTimeMillis();
    }

    @Override
    public boolean writeDeathCause(final String cause) {
        if (lastHumanWritten == null) {
            throw new IllegalStateException("Cannot write cause of death before a name has been written");
        }
        long currentTime = System.currentTimeMillis();
        if (currentTime - timeLastHumanWritten < 40) {
            lastHumanWritten.setCause(cause);
            return true;
        }
        return false;
    }

    @Override
    public boolean writeDetails(final String details) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'writeDetails'");
    }

    @Override
    public String getDeathCause(final String name) {
        Human h = new Human(name);
        for (Human k : humans) {
            if (k.equals(h)) {
                if (k.getCause().equals("")) {
                    return "heart attack";
                }
                return k.getCause();
            }
        }
        throw new IllegalArgumentException("Person does not exist inside death note");
    }

    @Override
    public String getDeathDetails(final String name) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'getDeathDetails'");
    }

    @Override
    public boolean isNameWritten(final String name) {
        return humans.contains(new Human(name));
    }    
}
