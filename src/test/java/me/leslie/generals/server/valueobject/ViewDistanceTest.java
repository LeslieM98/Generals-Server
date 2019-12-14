package me.leslie.generals.server.valueobject;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class ViewDistanceTest {
    @Test
    void correctValues() {
        assertDoesNotThrow(() -> new ViewDistance(1.0, 1.0, 2.0));
        assertDoesNotThrow(() -> new ViewDistance(10000.0, 114244.0, 99999999.0));
        assertDoesNotThrow(() -> new ViewDistance(1.0, 1535314.0, 215344514.0));
    }

    @Test
    void negativeNormalViewDistance() {
        assertThrows(IllegalStateException.class, () -> new ViewDistance(-1.0, 1.0, 1.0));
        assertThrows(IllegalStateException.class, () -> new ViewDistance(-100000.0, 1.0, 1.0));
        try {
            new ViewDistance(-1.0, 1.0, 1.0);
        } catch (Exception e) {
            String message = e.getMessage().toLowerCase();
            assertTrue(message.contains("normal"));
            assertTrue(message.contains("view"));
            assertTrue(message.contains("distance"));
            assertTrue(message.contains("positive"));
        }
    }

    @Test
    void zeroNormalViewDistance() {
        assertThrows(IllegalStateException.class, () -> new ViewDistance(0.0, 1.0, 1.0));
        try {
            new ViewDistance(0.0, 1.0, 1.0);
        } catch (Exception e) {
            String message = e.getMessage().toLowerCase();
            assertTrue(message.contains("normal"));
            assertTrue(message.contains("view"));
            assertTrue(message.contains("distance"));
            assertTrue(message.contains("positive"));
        }
    }

    @Test
    void negativeDisadvantagedViewDistance() {
        assertThrows(IllegalStateException.class, () -> new ViewDistance(1.0, -1.0, 1.0));
        assertThrows(IllegalStateException.class, () -> new ViewDistance(1.0, -100000.0, 1.0));
        try {
            new ViewDistance(1.0, -1.0, 1.0);
        } catch (Exception e) {
            String message = e.getMessage().toLowerCase();
            assertTrue(message.contains("disadvantaged"));
            assertTrue(message.contains("view"));
            assertTrue(message.contains("distance"));
            assertTrue(message.contains("positive"));
        }
    }

    @Test
    void zeroDisadvantagedViewDistance() {
        assertThrows(IllegalStateException.class, () -> new ViewDistance(1.0, 0.0, 1.0));
        try {
            new ViewDistance(1.0, 0.0, 1.0);
        } catch (Exception e) {
            String message = e.getMessage().toLowerCase();
            assertTrue(message.contains("disadvantaged"));
            assertTrue(message.contains("view"));
            assertTrue(message.contains("distance"));
            assertTrue(message.contains("positive"));
        }
    }

    @Test
    void negativeAdvantagedViewDistance() {
        assertThrows(IllegalStateException.class, () -> new ViewDistance(1.0, 1.0, -11.0));
        assertThrows(IllegalStateException.class, () -> new ViewDistance(1.0, 1.0, -100000.0));
        try {
            new ViewDistance(1.0, 1.0, -11.0);
        } catch (Exception e) {
            String message = e.getMessage().toLowerCase();
            assertTrue(message.contains("advantaged"));
            assertTrue(message.contains("view"));
            assertTrue(message.contains("distance"));
            assertTrue(message.contains("positive"));
        }
    }

    @Test
    void zeroAdvantagedViewDistance() {
        assertThrows(IllegalStateException.class, () -> new ViewDistance(1.0, 1.0, 0.0));
        try {
            new ViewDistance(1.0, 1.0, 0.0);
        } catch (Exception e) {
            String message = e.getMessage().toLowerCase();
            assertTrue(message.contains("advantaged"));
            assertTrue(message.contains("view"));
            assertTrue(message.contains("distance"));
            assertTrue(message.contains("positive"));
        }
    }
}
