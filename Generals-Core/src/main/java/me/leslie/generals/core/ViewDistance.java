package me.leslie.generals.core;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.ToString;

import java.io.Serializable;

@Getter
@ToString
@EqualsAndHashCode
public class ViewDistance implements Serializable {
    private final double normal;
    private final double disadvantaged;
    private final double advantaged;

    public ViewDistance(double normal, double disadvantaged, double advantaged) {
        this.normal = verifyValue(normal, "Value Normal has to be positive");
        this.disadvantaged = verifyValue(disadvantaged, "Value disadvantaged has to be positive");
        this.advantaged = verifyValue(advantaged, "Value advantaged has to be positive");
    }

    private double verifyValue(double value, String message) {
        if (value <= 0) {
            throw new IllegalStateException(message);
        }
        return value;
    }
}
