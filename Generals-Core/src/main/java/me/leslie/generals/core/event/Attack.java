package me.leslie.generals.core.event;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NonNull;
import lombok.ToString;
import me.leslie.generals.core.entity.MilitaryUnit;

@Getter
@EqualsAndHashCode
@ToString
public class Attack {
    @NonNull
    private final MilitaryUnit source;
    @NonNull
    private final MilitaryUnit target;
    private final int damage;

    public Attack(@NonNull MilitaryUnit source, @NonNull MilitaryUnit target, int damage) {
        this.source = source;
        this.target = target;
        if (damage <= 0) {
            throw new IllegalStateException("Damage must be at least 1");
        }
        this.damage = damage;
    }
}
