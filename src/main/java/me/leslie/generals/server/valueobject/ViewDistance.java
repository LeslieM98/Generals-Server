package me.leslie.generals.server.valueobject;

import lombok.Value;
import me.leslie.generals.server.util.Validators;


@Value
public class ViewDistance {
    double normal;
    double disadvantaged;
    double advantaged;

    public ViewDistance(double normal, double disadvantaged, double advantaged) {
        this.normal = Validators.isPositive(normal, "Normal view distance is not positive");
        this.disadvantaged = Validators.isPositive(disadvantaged, "disadvantaged view distance is not positive");
        this.advantaged = Validators.isPositive(advantaged, "advantaged view distance is not positive");
    }
}