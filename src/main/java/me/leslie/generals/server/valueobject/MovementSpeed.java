package me.leslie.generals.server.valueobject;

import lombok.Value;

@Value
public class MovementSpeed {
    double normal;
    double street;
    double difficultTerrain;

    public MovementSpeed(double normal, double street, double difficultTerrain){
        this.normal = validate(normal, "Normal speed has to be positive");
        this.street = validate(street, "Street speed has to be positive");
        this.difficultTerrain = validate(difficultTerrain, "Difficult terrain speed has to be positive");

    }

    private double validate(double value, String message) {
        if(value <= 0){
            throw new IllegalStateException(message);
        }
        return value;
    }
}
