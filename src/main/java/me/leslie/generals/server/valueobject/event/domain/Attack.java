package me.leslie.generals.server.valueobject.event.domain;

import lombok.EqualsAndHashCode;
import lombok.NonNull;
import lombok.Value;
import me.leslie.generals.server.util.Validators;
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
        Validators.areNotEqual(source, target, "Source and Target are equal");
        this.source = source;
        this.target = target;
        this.damage = Validators.isPositive(damage, "Damage must be at least 1");
    }

}
