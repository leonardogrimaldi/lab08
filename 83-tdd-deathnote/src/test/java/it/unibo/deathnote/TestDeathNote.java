package it.unibo.deathnote;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertNotEquals;
import static org.junit.jupiter.api.Assertions.assertNull;
import static org.junit.jupiter.api.Assertions.assertTrue;
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
    private String details;

    @BeforeEach
    public void setUp() {
        deathNote = new DeathNoteImpl();
        name = "Leonardo Grimaldi";
        cause = "cardiac arrest";
        details = "ran for too long";
    }

    /**
     * Test number 1
     */
    @Test
    public void testRuleZeroAndNegativeRules() {
        try {
            deathNote.getRule(0);
            deathNote.getRule(-1);
            fail("Rule 0 exists");
        } catch (IllegalArgumentException e) {
            assertEquals("Cannot access rule number 0 and negative rules as they do not exist", e.getMessage());
        }

        try {
            deathNote.getRule(-1);
            fail("Rule -1 exists");
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
        } catch (NullPointerException e) {
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
        Assertions.assertTrue(deathNote.writeDeathCause("karting accident"));
        try {
            TimeUnit.MILLISECONDS.sleep(100);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        assertFalse(deathNote.writeDeathCause("car accident"));
    }

    @Test
    public void testDeath() {
        try {
            deathNote.writeDetails(details);
            fail("Details of death were written before a name was written");
        } catch (IllegalStateException e) {
            assertEquals("Cannot write details of death before a name is written in the death note", e.getMessage());
        }
        deathNote.writeName(name);
        assertEquals("", deathNote.getDeathDetails(name));
        assertTrue(deathNote.writeDetails(details));
        assertEquals(details, deathNote.getDeathDetails(name));
        deathNote.writeName("Light Yagami");
        try {
            TimeUnit.MILLISECONDS.sleep(6100);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        assertFalse(deathNote.writeDetails("fell while running"));
        assertNotEquals("fell while running", deathNote.getDeathDetails(name));
    }

    @Test
    public void sameHumans() {
        final Human leo1 = new Human(name);
        final Human leo2 = new Human(name);

        assertTrue(leo1.equals(leo2));
        assertTrue(leo2.equals(leo1));  // Symmetry
        assertTrue(leo1.equals(leo1));
        assertFalse(leo1.equals(new Object()));
        assertFalse(leo1.equals(new Human("Light Yagami")));
    }
}