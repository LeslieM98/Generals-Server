package me.leslie.generals.server.valueobject;

import lombok.Value;


@Value
public class CombatRange {
    double close;
    double ranged;

    public CombatRange(double close, double ranged) {
        this.close = validate(close, "Close range has to be positive");
        this.ranged = validate(ranged, "Ranged has to be positive");
    }


    private double validate(double value, String message) {
        if(value <= 0){
            throw new IllegalStateException(message);
        }
        return value;
    }
}