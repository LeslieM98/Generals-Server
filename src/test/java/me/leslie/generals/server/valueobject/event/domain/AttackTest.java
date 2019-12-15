package me.leslie.generals.server.valueobject.event.domain;

import me.leslie.generals.server.valueobject.TroopID;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class AttackTest {
    private final TroopID t1 = new TroopID(1);
    private final TroopID t2 = new TroopID(2);
    private final TroopID t3 = new TroopID(2);

    @Test
    void testCorrectCreation() {
        Attack a = new Attack(t1, t2, 9);
        assertNotNull(a);
    }

    @Test
    void testSameTroops() {
        assertThrows(IllegalStateException.class, () -> new Attack(t1, t1, 9));
        try {
            new Attack(t1, t1, 9);
        } catch (Exception e) {
            String message = e.getMessage().toLowerCase();
            assertTrue(message.contains("source"));
            assertTrue(message.contains("target"));
            assertTrue(message.contains("equal"));
        }
    }

    @Test
    void testEqualTroops() {
        assertThrows(IllegalStateException.class, () -> new Attack(t2, t3, 9));
        try {
            new Attack(t2, t3, 9);
        } catch (Exception e) {
            String message = e.getMessage().toLowerCase();
            assertTrue(message.contains("source"));
            assertTrue(message.contains("target"));
            assertTrue(message.contains("equal"));
        }
    }

    @Test
    void testInvalidAttackDamage() {
        assertThrows(IllegalStateException.class, () -> new Attack(t1, t2, 0));
        assertThrows(IllegalStateException.class, () -> new Attack(t1, t2, -1));
    }

    @Test
    void nullTroopIDs() {
        assertThrows(NullPointerException.class, () -> new Attack(null, t2, 1));
        assertThrows(NullPointerException.class, () -> new Attack(t1, null, 1));
    }
}
