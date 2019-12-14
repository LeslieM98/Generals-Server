package me.leslie.generals.server.valueobject;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class CombatRangeTest {
    @Test
    void correctValues() {
        assertDoesNotThrow(() -> new CombatRange(1.0, 1.0));
        assertDoesNotThrow(() -> new CombatRange(10000.0, 114244.0));
        assertDoesNotThrow(() -> new CombatRange(1.0, 1535314.0));
    }

    @Test
    void negativeCloseRange() {
        assertThrows(IllegalStateException.class, () -> new CombatRange(-1.0, 1.0));
        assertThrows(IllegalStateException.class, () -> new CombatRange(-100000.0, 1.0));
        try {
            new CombatRange(-1.0, 1.0);
        } catch (Exception e) {
            String message = e.getMessage().toLowerCase();
            assertTrue(message.contains("close"));
            assertTrue(message.contains("positive"));
        }
    }

    @Test
    void zeroCloseRange() {
        assertThrows(IllegalStateException.class, () -> new CombatRange(0.0, 1.0));
        try {
            new CombatRange(0.0, 1.0);
        } catch (Exception e) {
            String message = e.getMessage().toLowerCase();
            assertTrue(message.contains("close"));
            assertTrue(message.contains("positive"));
        }
    }

    @Test
    void negativeRanged() {
        assertThrows(IllegalStateException.class, () -> new CombatRange(1.0, -1.0));
        assertThrows(IllegalStateException.class, () -> new CombatRange(1.0, -100000.0));
        try {
            new CombatRange(1.0, -1.0);
        } catch (Exception e) {
            String message = e.getMessage().toLowerCase();
            assertTrue(message.contains("ranged"));
            assertTrue(message.contains("positive"));
        }
    }

    @Test
    void zeroRanged() {
        assertThrows(IllegalStateException.class, () -> new CombatRange(1.0, 0.0));
        try {
            new CombatRange(1.0, 0.0);
        } catch (Exception e) {
            String message = e.getMessage().toLowerCase();
            assertTrue(message.contains("ranged"));
            assertTrue(message.contains("positive"));
        }
    }
}
