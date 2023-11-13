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
}