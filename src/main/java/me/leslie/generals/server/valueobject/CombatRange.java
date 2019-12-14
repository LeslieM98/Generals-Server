package me.leslie.generals.server.valueobject;

import lombok.Value;
import me.leslie.generals.server.util.Validators;


@Value
public class CombatRange {
    double close;
    double ranged;

    public CombatRange(double close, double ranged) {
        this.close = Validators.isPositive(close, "Close range is not positive");
        this.ranged = Validators.isPositive(ranged, "Ranged is not positive");
    }
}