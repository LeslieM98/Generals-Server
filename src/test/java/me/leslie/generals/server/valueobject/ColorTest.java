package me.leslie.generals.server.valueobject;
import org.junit.jupiter.api.Assertions;
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
    void testCorrectHexparsing() {
        assertColor(new Color("ff00ff"), 255, 0, 255, "ff00ff");
        assertColor(new Color("00ff0f"), 0, 255, 15, "00ff0f");
        assertColor(new Color("000000"), 0, 0, 0, "000000");
    }

    @Test
    void testCorrectRGBParsing() {
        assertColor(new Color(255, 0, 255), 255, 0, 255, "ff00ff");
        assertColor(new Color(0, 255, 16), 0, 255, 16, "00ff10");
        assertColor(new Color(0, 0, 0), 0, 0, 0, "000000");
    }

    @Test
    void testIncorrectConstructorValues() {
        Assertions.assertThrows(IllegalStateException.class, () -> new Color(256, 0, 0));
        Assertions.assertThrows(IllegalStateException.class, () -> new Color(-1, 0, 0));
        Assertions.assertThrows(IllegalStateException.class, () -> new Color(0, 256, 0));
        Assertions.assertThrows(IllegalStateException.class, () -> new Color(0, -1, 0));
        Assertions.assertThrows(IllegalStateException.class, () -> new Color(0, 0, 256));
        Assertions.assertThrows(IllegalStateException.class, () -> new Color(0, 0, -1));
        Assertions.assertThrows(IllegalStateException.class, () -> new Color("ff"));
        Assertions.assertThrows(IllegalStateException.class, () -> new Color("fffff"));
        Assertions.assertThrows(IllegalStateException.class, () -> new Color("fffffff"));
        Assertions.assertThrows(NumberFormatException.class, () -> new Color("fffffg"));
    }

    @Test
    void testEquals() {
        assertEquals(new Color("23467f"), new Color("23467f"));
        assertNotEquals(new Color("23467f"), new Color("24467f"));
    }


}
