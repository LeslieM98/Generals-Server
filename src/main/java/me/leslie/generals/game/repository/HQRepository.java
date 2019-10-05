package me.leslie.generals.game.repository;

import me.leslie.generals.game.entity.Army;
import me.leslie.generals.game.entity.HQ;
import me.leslie.generals.utility.AttackDistance;
import me.leslie.generals.utility.MovementSpeed;
import me.leslie.generals.utility.Vector2D;
import me.leslie.generals.utility.ViewDistance;

import java.util.List;

public interface HQRepository {
    HQ createHQ(int currentHealth, int maxHealth, Vector2D position, MovementSpeed movementSpeed, AttackDistance attackDistance, ViewDistance viewDistance, Army army);

    HQ updateHQ(HQ army);

    HQ deleteHQ(HQ army);

    HQ get(int id);

    List<HQ> getHQs();
}
