package me.leslie.generals.server.util;

public class Validators {
    private Validators() {
    }

    public static double isPositive(double value, String message) {
        if (value <= 0) {
            throw new IllegalStateException(message);
        }
        return value;
    }
}
