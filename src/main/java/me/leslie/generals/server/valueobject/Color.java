package me.leslie.generals.server.valueobject;

import lombok.Value;

import java.io.Serializable;

@Value
public class Color implements Serializable {
    private final int red;
    private final int green;
    private final int blue;

    public Color(int red, int green, int blue) {
        this.red = verifyColorValue(red);
        this.green = verifyColorValue(green);
        this.blue = verifyColorValue(blue);
    }

    public Color(String hex) {
        this(parseRed(hex), parseGreen(hex), parseBlue(hex));
    }

    private static int parseRed(String hex) {
        if (hex.length() != 6) {
            throw new IllegalStateException("Colorvalue Red in hex has to be 6 characters long");
        }
        return Integer.parseInt(hex.substring(0, 2), 16);
    }

    private static int parseGreen(String hex) {
        if (hex.length() != 6) {
            throw new IllegalStateException("Colorvalue Green in hex has to be 6 characters long");
        }
        return Integer.parseInt(hex.substring(2, 4), 16);
    }

    private static int parseBlue(String hex) {
        if (hex.length() != 6) {
            throw new IllegalStateException("Hex blue is not 6 characters long");
        }
        return Integer.parseInt(hex.substring(4, 6), 16);
    }

    private int verifyColorValue(int colorValue) {
        if (colorValue <= 255 && colorValue >= 0) {
            return colorValue;
        } else {
            throw new IllegalStateException("value is not between 255 and 0");
        }
    }

    private String valueToHex(int value) {
        String hex = Integer.toString(value, 16);
        if (value < 16) {
            hex = "0" + hex;
        }
        return hex;
    }

    public String toHex() {
        return valueToHex(red) +
                valueToHex(green) +
                valueToHex(blue);
    }

    @Override
    public String toString() {
        return "Color{#" + toHex() + '}';
    }
}
