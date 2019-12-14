package me.leslie.generals.server.valueobject;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class HealthTest {

    @Test
    void validHealth() {
        assertDoesNotThrow(() -> new Health(10, 2));
        assertDoesNotThrow(() -> new Health(10, 10));
        assertDoesNotThrow(() -> new Health(1, 1));
        assertDoesNotThrow(() -> new Health(1, 0));
        assertDoesNotThrow(() -> new Health(1, -2));
    }

    @Test
    void zeroMaximum(){
        assertThrows(IllegalStateException.class, () -> new Health(0, 0));
    }

    @Test
    void negativeMaximum(){
        assertThrows(IllegalStateException.class, () -> new Health(-1, -1));
    }


    @Test
    void currentAboveMaximum(){
        assertThrows(IllegalStateException.class, () -> new Health(100, 101));
    }

    @Test
    void isAlive(){
        Health h1 = new Health(100, 100);
        Health h2 = new Health(100, 1);
        Health h3 = new Health(100, 50);
        Health h4 = new Health(1, 1);

        assertFalse(h1.isDead());
        assertFalse(h2.isDead());
        assertFalse(h3.isDead());
        assertFalse(h4.isDead());

        assertTrue(h1.isAlive());
        assertTrue(h2.isAlive());
        assertTrue(h3.isAlive());
        assertTrue(h4.isAlive());
    }

    @Test
    void isDead(){
        Health h1 = new Health(100, 0);
        Health h2 = new Health(100, -1);
        Health h3 = new Health(1, 0);
        Health h4 = new Health(1, -1);

        assertTrue(h1.isDead());
        assertTrue(h2.isDead());
        assertTrue(h3.isDead());
        assertTrue(h4.isDead());

        assertFalse(h1.isAlive());
        assertFalse(h2.isAlive());
        assertFalse(h3.isAlive());
        assertFalse(h4.isAlive());
    }
}
