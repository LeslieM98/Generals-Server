package me.leslie.generals.server.valueobject.event.domain;

import lombok.NonNull;
import lombok.Value;
import me.leslie.generals.server.util.Validators;
import me.leslie.generals.server.valueobject.TroopID;

@Value
public class Attack implements DomainEvent {
    @NonNull
    TroopID source;
    @NonNull
    TroopID target;
    int damage;

    public Attack(@NonNull TroopID source, @NonNull TroopID target, int damage) {
        Validators.areNotEqual(source, target, "Source and Target are equal");
        this.source = source;
        this.target = target;
        this.damage = Validators.isPositive(damage, "Damage must be at least 1");
    }
}
