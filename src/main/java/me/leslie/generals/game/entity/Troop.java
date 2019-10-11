package me.leslie.generals.game.entity;

import lombok.*;
import me.leslie.generals.utility.CombatRange;
import me.leslie.generals.utility.MovementSpeed;
import me.leslie.generals.utility.Vector2D;
import me.leslie.generals.utility.ViewDistance;

import java.io.Serializable;
import java.util.List;
import java.util.Objects;
import java.util.Optional;

@Getter
@Builder
@ToString
@AllArgsConstructor
public class Troop implements MilitaryUnit, Serializable {

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
    public int getCurrentHealth() {
        return currentHealth;
    }

    @Override
    public int getMaxHealth() {
        return maxHealth;
    }

    @Override
    public Optional<List<MilitaryUnit>> getChildren() {
        return Optional.empty();
    }

    @Override
    public long getID() {
        return id;
    }

    @Override
    public Vector2D getPos() {
        return position;
    }

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
