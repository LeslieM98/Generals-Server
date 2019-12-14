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

    public static int isPositive(int value, String message) {
        if (value <= 0) {
            throw new IllegalStateException(message);
        }
        return value;
    }

    public static void areNotEqual(Object a, Object b, String message) {
        if(a.equals(b)){
            throw new IllegalStateException(message);
        }
    }
}
