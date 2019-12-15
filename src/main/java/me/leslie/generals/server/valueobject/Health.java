package me.leslie.generals.server.valueobject;

import lombok.Value;
import me.leslie.generals.server.util.Validators;

@Value
public class Health {
    int maximum;
    int current;

   public Health(int maximum, int current){
        this.maximum = Validators.isPositive(maximum, "Maximum is not positive");
       this.current = validateCurrent(maximum, current);
    }


    private int validateCurrent(int maximum, int current){
        if(current > maximum){
            throw new IllegalStateException("Current is greater than maximum");
        }
        return current;
    }

    public boolean isDead() {
        return current <= 0;
    }

    public boolean isAlive() {
        return !isDead();
    }

    public Health setCurrent(int current) {
        return new Health(maximum, current);
    }
}
