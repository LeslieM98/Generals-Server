package me.leslie.generals.server.valueobject;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class Vector2DTest {
    @Test
    void testPositiveCoordinates(){
        assertEquals(1, new Vector2D(1, 0).calculateLength());
        assertEquals(1, new Vector2D(0, 1).calculateLength());
        assertEquals(10, new Vector2D(0, 10).calculateLength());
        assertEquals(10, new Vector2D(10, 0).calculateLength());
        assertEquals(5, new Vector2D(3, 4).calculateLength());
        assertEquals(5, new Vector2D(4, 3).calculateLength());
    }

    @Test
    void testNegativeCoordinates(){
        assertEquals(1, new Vector2D(-1, 0).calculateLength());
        assertEquals(1, new Vector2D(0, -1).calculateLength());
        assertEquals(10, new Vector2D(0, -10).calculateLength());
        assertEquals(10, new Vector2D(-10, 0).calculateLength());
        assertEquals(5, new Vector2D(-3, -4).calculateLength());
        assertEquals(5, new Vector2D(-4, -3).calculateLength());
    }
}
