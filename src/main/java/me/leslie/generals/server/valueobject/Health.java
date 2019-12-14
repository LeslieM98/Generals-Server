package me.leslie.generals.server.valueobject;

import lombok.Value;
import me.leslie.generals.server.util.Validators;

@Value
public class Health {
    int maximum;
    int current;

   public Health(int maximum, int current){
        this.maximum = Validators.isPositive(maximum, "Maximum cannot be 0 or negative");
        this.current = validateCurrent(maximum, current);
    }


    private int validateCurrent(int maximum, int current){
        if(current > maximum){
            throw new IllegalStateException("Current cannot be greater than maximum");
        }
        return current;
    }

    public boolean isDead(){
        return current <= 0;
    }

    public boolean isAlive(){
        return !isDead();
    }
}
