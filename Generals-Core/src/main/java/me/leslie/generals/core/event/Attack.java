package me.leslie.generals.core.event;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NonNull;
import lombok.ToString;
import me.leslie.generals.core.entity.Troop;

@Getter
@EqualsAndHashCode
@ToString
public class Attack {
    @NonNull
    private final Troop source;
    @NonNull
    private final Troop target;
    private final int damage;

    public Attack(@NonNull Troop source, @NonNull Troop target, int damage) {
        if (damage <= 0) {
            throw new IllegalStateException("Damage must be at least 1");
        }

        if (source.equals(target)) {
            throw new IllegalStateException("Source and Target cannot be the same");
        }

        this.source = source;
        this.target = target;
        this.damage = damage;
    }
}
