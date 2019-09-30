package me.leslie.generals.dedicated.server.valueobject;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class ColorTest {

    private void assertColor(Color color, int expectedRed, int expectedGreen, int expectedBlue, String expectedHex) {
        assertEquals(expectedRed, color.getRed());
        assertEquals(expectedGreen, color.getGreen());
        assertEquals(expectedBlue, color.getBlue());
        assertEquals(expectedHex, color.toHex());
        assertTrue(color.toString().contains(expectedHex));
    }

    @Test
    public void testCorrectHexparsing() {
        assertColor(new Color("ff00ff"), 255, 0, 255, "ff00ff");
        assertColor(new Color("00ff0f"), 0, 255, 15, "00ff0f");
        assertColor(new Color("000000"), 0, 0, 0, "000000");
    }

    @Test
    public void testCorrectRGBParsing() {
        assertColor(new Color(255, 0, 255), 255, 0, 255, "ff00ff");
        assertColor(new Color(0, 255, 16), 0, 255, 16, "00ff10");
        assertColor(new Color(0, 0, 0), 0, 0, 0, "000000");
    }

    @Test
    public void testIncorrectConstructorValues() {
        assertThrows(IllegalStateException.class, () -> new Color(256, 0, 0));
        assertThrows(IllegalStateException.class, () -> new Color(-1, 0, 0));
        assertThrows(IllegalStateException.class, () -> new Color(0, 256, 0));
        assertThrows(IllegalStateException.class, () -> new Color(0, -1, 0));
        assertThrows(IllegalStateException.class, () -> new Color(0, 0, 256));
        assertThrows(IllegalStateException.class, () -> new Color(0, 0, -1));
        assertThrows(IllegalStateException.class, () -> new Color("ff"));
        assertThrows(IllegalStateException.class, () -> new Color("fffff"));
        assertThrows(IllegalStateException.class, () -> new Color("fffffff"));
        assertThrows(NumberFormatException.class, () -> new Color("fffffg"));
    }

    @Test
    public void testEquals() {
        assertEquals(new Color("23467f"), new Color("23467f"));
        assertNotEquals(new Color("23467f"), new Color("24467f"));
    }


}
