package me.leslie.generals.server.model;

import lombok.*;
import me.leslie.generals.server.valueobject.*;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Value
@Document("Troop")
public class Troop {
    @Id
    TroopID id;
    Integer currentHealth;
    Integer maxHealth;
    Vector2D position;
    MovementSpeed movementSpeed;
    CombatRange combatRange;
    ViewDistance viewDistance;
}
