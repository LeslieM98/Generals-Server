package me.leslie.generals.dedicated.server.valueobject;

import java.util.Objects;

public class Color {
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
            throw new IllegalStateException("Colorvalue in hex has to be 6 characters long");
        }
        return Integer.parseInt(hex.substring(0, 2), 16);
    }

    private static int parseGreen(String hex) {
        if (hex.length() != 6) {
            throw new IllegalStateException("Colorvalue in hex has to be 6 characters long");
        }
        return Integer.parseInt(hex.substring(2, 4), 16);
    }

    private static int parseBlue(String hex) {
        if (hex.length() != 6) {
            throw new IllegalStateException("Colorvalue in hex has to be 6 characters long");
        }
        return Integer.parseInt(hex.substring(4, 6), 16);
    }

    private int verifyColorValue(int colourValue) {
        if (colourValue <= 255 && colourValue >= 0) {
            return colourValue;
        } else {
            throw new IllegalStateException("Colourvalue has to be between 255 and 0");
        }
    }

    public int getRed() {
        return red;
    }

    public int getGreen() {
        return green;
    }

    public int getBlue() {
        return blue;
    }

    private String valueToHex(int value) {
        String hex = Integer.toString(value, 16);
        if (value < 16) {
            hex = "0" + hex;
        }
        return hex;
    }

    public String toHex() {
        return new StringBuilder(valueToHex(red))
                .append(valueToHex(green))
                .append(valueToHex(blue))
                .toString();
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Color color = (Color) o;
        return red == color.red &&
                green == color.green &&
                blue == color.blue;
    }

    @Override
    public int hashCode() {
        return Objects.hash(red, green, blue);
    }

    @Override
    public String toString() {
        return "Color{#" + toHex() + '}';
    }
}
