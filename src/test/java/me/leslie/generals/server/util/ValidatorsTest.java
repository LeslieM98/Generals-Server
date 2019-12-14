package me.leslie.generals.server.util;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class ValidatorsTest {
    @Test
    void positiveValueInIsPositive() {
        assertDoesNotThrow(() -> Validators.isPositive(1.0, "Should never show up anywhere"));
        assertDoesNotThrow(() -> Validators.isPositive(99.0, "Should never show up anywhere"));
        assertDoesNotThrow(() -> Validators.isPositive(999999.0, "Should never show up anywhere"));
        assertDoesNotThrow(() -> Validators.isPositive(1000.0, "Should never show up anywhere"));
    }

    @Test
    void negativeValueInIsPositive() {
        assertThrows(IllegalStateException.class, () -> Validators.isPositive(-1.0, "Should show up"));
        assertThrows(IllegalStateException.class, () -> Validators.isPositive(-99.0, "Should show up"));
        assertThrows(IllegalStateException.class, () -> Validators.isPositive(-999999.0, "Should show up"));
        assertThrows(IllegalStateException.class, () -> Validators.isPositive(-1000.0, "Should show up"));
    }

    @Test
    void zeroValueInIsPositive() {
        assertThrows(IllegalStateException.class, () -> Validators.isPositive(0.0, "Should show up"));
    }

    @Test
    void correctMessageInExceptionInIsPositive() {
        try {
            assertThrows(IllegalStateException.class, () -> Validators.isPositive(0.0, "Should show up"));
        } catch (Exception e) {
            assertEquals("Should show up", e.getMessage());
        }
    }

}
