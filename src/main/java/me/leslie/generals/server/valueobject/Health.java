package me.leslie.generals.server.valueobject;

import lombok.Value;

@Value
public class Health {
    int maximum;
    int current;

    public Health(int maximum, int current){
        validate(maximum, current);
        this.maximum = maximum;
        this.current = current;
    }

    private void validate(int maximum, int current){
        validateMaximum(maximum);
        validateCurrent(maximum, current);
    }

    private void validateCurrent(int maximum, int current){
        if(current > maximum){
            throw new IllegalStateException("Current cannot be higher than maximum");
        }
    }

    private void validateMaximum(int maximum){
        if(maximum <= 0){
            throw new IllegalStateException("Maximum cannot be 0 or negative");
        }
    }

    public boolean isDead(){
        return current <= 0;
    }

    public boolean isAlive(){
        return !isDead();
    }
}
