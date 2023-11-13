package it.unibo.deathnote;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.fail;

import java.util.concurrent.TimeUnit;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import it.unibo.deathnote.api.DeathNote;
import it.unibo.deathnote.impl.DeathNoteImpl;

class TestDeathNote {

    private DeathNote deathNote;
    private String name;
    private String cause;

    @BeforeEach
    public void setUp() {
        deathNote = new DeathNoteImpl();
        name = "Leonardo Grimaldi";
        cause = "Cardiac arrest from drinking too many energy drinks";
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

    @Test
    public void testCauseOfDeath() {
        try {
            deathNote.writeDeathCause(cause);
            fail("The death cause has been written before a name was written");
        } catch (IllegalStateException e) {
            Assertions.assertEquals("Cannot write cause of death before a name has been written", e.getMessage());
        }
        deathNote.writeName(name);
        deathNote.writeDeathCause(cause);
        Assertions.assertEquals(cause, deathNote.getDeathCause(name));
        deathNote.writeName("Light Yagami");
        Assertions.assertEquals(true, deathNote.writeDeathCause("Karting accident"));
        try {
            TimeUnit.MILLISECONDS.sleep(100);
        } catch (InterruptedException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        assertEquals(false, deathNote.writeDeathCause("Car accident"));
    }
}