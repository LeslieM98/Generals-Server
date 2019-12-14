package me.leslie.generals.server.valueobject;

import lombok.Value;


@Value
public class ViewDistance {
    double normal;
    double disadvantaged;
    double advantaged;

    public ViewDistance(double normal, double disadvantaged, double advantaged) {
        this.normal = verifyValue(normal, "Normal view distance has to be positive");
        this.disadvantaged = verifyValue(disadvantaged, "disadvantaged view distance has to be positive");
        this.advantaged = verifyValue(advantaged, "advantaged view distance has to be positive");
    }

    private double verifyValue(double value, String message) {
        if (value <= 0) {
            throw new IllegalStateException(message);
        }
        return value;
    }
}