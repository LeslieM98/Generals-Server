package me.leslie.generals.game.entity;

import lombok.Getter;
import lombok.NonNull;
import lombok.ToString;
import me.leslie.generals.utility.AttackDistance;
import me.leslie.generals.utility.MovementSpeed;
import me.leslie.generals.utility.Vector2D;
import me.leslie.generals.utility.ViewDistance;

import java.io.Serializable;

@ToString
@Getter
public class HQ extends Troup implements Serializable {
    @NonNull
    private final Army armyID;

    public HQ(int id, int currentHealth, int maxHealth, @NonNull Vector2D position, @NonNull MovementSpeed movementSpeed, @NonNull AttackDistance attackDistance, @NonNull ViewDistance viewDistance, Army army) {
        super(id, currentHealth, maxHealth, position, movementSpeed, attackDistance, viewDistance);
        this.armyID = army;
    }


}
