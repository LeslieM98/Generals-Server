package me.leslie.generals.server.util;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class ValidatorsTest {
    static class IsPositiveDouble {
        @Test
        void positiveValues() {
            assertDoesNotThrow(() -> Validators.isPositive(1.0, "Should never show up anywhere"));
            assertDoesNotThrow(() -> Validators.isPositive(99.0, "Should never show up anywhere"));
            assertDoesNotThrow(() -> Validators.isPositive(999999.0, "Should never show up anywhere"));
            assertDoesNotThrow(() -> Validators.isPositive(1000.0, "Should never show up anywhere"));
        }

        @Test
        void negativeValues() {
            assertThrows(IllegalStateException.class, () -> Validators.isPositive(-1.0, "Should show up"));
            assertThrows(IllegalStateException.class, () -> Validators.isPositive(-99.0, "Should show up"));
            assertThrows(IllegalStateException.class, () -> Validators.isPositive(-999999.0, "Should show up"));
            assertThrows(IllegalStateException.class, () -> Validators.isPositive(-1000.0, "Should show up"));
        }

        @Test
        void zeroValue() {
            assertThrows(IllegalStateException.class, () -> Validators.isPositive(0.0, "Should show up"));
        }

        @Test
        void correctMessage() {
            try {
                assertThrows(IllegalStateException.class, () -> Validators.isPositive(0.0, "Should show up"));
            } catch (Exception e) {
                assertEquals("Should show up", e.getMessage());
            }
        }
    }

    static class IsPositiveInt {
        @Test
        void positiveValues() {
            assertDoesNotThrow(() -> Validators.isPositive(1, "Should never show up anywhere"));
            assertDoesNotThrow(() -> Validators.isPositive(99, "Should never show up anywhere"));
            assertDoesNotThrow(() -> Validators.isPositive(999999, "Should never show up anywhere"));
            assertDoesNotThrow(() -> Validators.isPositive(1000, "Should never show up anywhere"));
        }

        @Test
        void negativeValues() {
            assertThrows(IllegalStateException.class, () -> Validators.isPositive(-1, "Should show up"));
            assertThrows(IllegalStateException.class, () -> Validators.isPositive(-99, "Should show up"));
            assertThrows(IllegalStateException.class, () -> Validators.isPositive(-999999, "Should show up"));
            assertThrows(IllegalStateException.class, () -> Validators.isPositive(-1000, "Should show up"));
        }

        @Test
        void zeroValue() {
            assertThrows(IllegalStateException.class, () -> Validators.isPositive(0, "Should show up"));
        }

        @Test
        void correctMessageInException() {
            try {
                assertThrows(IllegalStateException.class, () -> Validators.isPositive(0, "Should show up"));
            } catch (Exception e) {
                assertEquals("Should show up", e.getMessage());
            }
        }
    }

    static class areNotEqual {
        @Test
        void unequalObjects() {
            Integer i1 = 1;
            Integer i2 = 2;

            assertDoesNotThrow(() -> Validators.areNotEqual(i1, i2, "Should never show up anywhere"));
        }

        @Test
        void equalObjects() {
            Integer i1 = 1;
            Integer i2 = 1;
            assertThrows(IllegalStateException.class, () ->  Validators.areNotEqual(i1, i2,"Should show up"));
        }

        @Test
        void zeroValue() {
            Integer i = 1;
            assertThrows(IllegalStateException.class, () ->  Validators.areNotEqual(i, i,"Should show up"));
        }

        @Test
        void correctMessageInException() {
            Integer i = 1;
            try {
                assertThrows(IllegalStateException.class, () -> Validators.areNotEqual(i, i,"Should show up"));
            } catch (Exception e) {
                assertEquals("Should show up", e.getMessage());
            }
        }
    }
}
