package me.leslie.generals.game.repository;

import me.leslie.generals.game.entity.Troup;
import me.leslie.generals.utility.AttackDistance;
import me.leslie.generals.utility.MovementSpeed;
import me.leslie.generals.utility.Vector2D;
import me.leslie.generals.utility.ViewDistance;

import java.util.List;

public interface TroupRepository {
    Troup createTroup(int currentHealth, int maxHealth, Vector2D position, MovementSpeed movementSpeed, AttackDistance attackDistance, ViewDistance viewDistance);

    Troup updateTroup(Troup troup);

    Troup deleteTroup(Troup troup);

    Troup getTroup(int id);

    List<Troup> getTroups();
}
