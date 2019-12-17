package me.leslie.generals.server.model.gameentity;

import lombok.Value;
import me.leslie.generals.server.valueobject.*;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Value
@Document("Troop")
public class Troop {
    @Id
    TroopID id;
    Health health;
    Vector2D position;
    MovementSpeed movementSpeed;
    CombatRange combatRange;
    ViewDistance viewDistance;

    public Troop recieveDamage(int damage) {
        return new Troop(
                id,
                health.recieveDamage(damage),
                position,
                movementSpeed,
                combatRange,
                viewDistance
        );
    }

    public Troop move(Vector2D updatedPosition) {
        return new Troop(
                id,
                health,
                updatedPosition,
                movementSpeed,
                combatRange,
                viewDistance
        );
    }
}
