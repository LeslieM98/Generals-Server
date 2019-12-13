package me.leslie.generals.server.valueobject.domainevent;

import lombok.EqualsAndHashCode;
import lombok.NonNull;
import lombok.Value;
import me.leslie.generals.server.valueobject.TroopID;

@EqualsAndHashCode(callSuper = true)
@Value
public class Attack extends DomainEvent {
    @NonNull
    private final TroopID source;
    @NonNull
    private final TroopID target;
    private final int damage;

    public Attack(@NonNull TroopID source, @NonNull TroopID target, int damage) {
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
