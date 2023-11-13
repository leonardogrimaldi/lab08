package it.unibo.deathnote;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.fail;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import it.unibo.deathnote.api.DeathNote;
import it.unibo.deathnote.impl.DeathNoteImpl;

class TestDeathNote {

    private DeathNote deathNote;
    private String name;

    @BeforeEach
    public void setUp() {
        deathNote = new DeathNoteImpl();
    }


    @Test
    public void testRuleZeroAndNegativeRules() {
        try {
            deathNote.getRule(0);
            deathNote.getRule(-1);
            fail("Rules 0 and -1 exist");
        } catch (IllegalArgumentException e) {
            assertEquals("Cannot access rule number 0 and negative rules as they do not exist", e.getMessage());
        }
    }

    @Test
    public void testRulesNotNullOrEmpty() {
        for (String rule : DeathNote.RULES) {
            Assertions.assertNotEquals("", rule);
            Assertions.assertNotEquals(null, rule);
        }
    }

    @Test
    public void testNameInsertion() {
        name = "Leonardo Grimaldi";
        Assertions.assertFalse(deathNote.isNameWritten(name));
        deathNote.writeName(name);
        Assertions.assertTrue(deathNote.isNameWritten(name));
        try {
            deathNote.writeName(name);
            fail("User was allowed to insert duplicate name");
        } catch (IllegalArgumentException e) {
            assertEquals("Cannot insert the same person", e.getMessage());
        }
        try {
            deathNote.writeName("");
            fail("User was allowed to insert empty string as name");
        } catch (IllegalArgumentException e) {
            assertEquals("Cannot insert empty string inside death note", e.getMessage());
        }
        try {
            /*Test null string insertion even though exercise doesn't ask*/
            deathNote.writeName(null);
            fail("User was allowed to insert null string");
        } catch (IllegalArgumentException e) {
            assertEquals("Cannot insert null string inside death note", e.getMessage());
        }
        

    }
}