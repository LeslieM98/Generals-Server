package me.leslie.generals.core.entity;

import lombok.*;
import me.leslie.generals.core.CombatRange;
import me.leslie.generals.core.MovementSpeed;
import me.leslie.generals.core.Vector2D;
import me.leslie.generals.core.ViewDistance;

import java.io.Serializable;
import java.util.Objects;

@Getter
@Builder
@ToString
@AllArgsConstructor
public class Troop implements Serializable {

    private final long id;
    private final int currentHealth;
    private final int maxHealth;
    @NonNull
    private final Vector2D position;
    @NonNull
    private final MovementSpeed movementSpeed;
    @NonNull
    private final CombatRange combatRange;
    @NonNull
    private final ViewDistance viewDistance;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Troop troup = (Troop) o;
        return id == troup.id;
    }

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }

    public TroopBuilder copy() {
        return builder()
                .id(id)
                .currentHealth(currentHealth)
                .maxHealth(maxHealth)
                .position(position)
                .movementSpeed(movementSpeed)
                .combatRange(combatRange)
                .viewDistance(viewDistance);
    }

}
