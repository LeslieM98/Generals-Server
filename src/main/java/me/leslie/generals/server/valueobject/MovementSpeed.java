package me.leslie.generals.server.valueobject;

import lombok.Value;
import me.leslie.generals.server.util.Validators;

@Value
public class MovementSpeed {
    double normal;
    double street;
    double difficultTerrain;

    public MovementSpeed(double normal, double street, double difficultTerrain) {
        this.normal = Validators.isPositive(normal, "Normal speed is not positive");
        this.street = Validators.isPositive(street, "Street speed is not positive");
        this.difficultTerrain = Validators.isPositive(difficultTerrain, "Difficult terrain speed is not positive");
    }
}
