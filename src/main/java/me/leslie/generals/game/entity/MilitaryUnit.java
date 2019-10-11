package me.leslie.generals.game.entity;

import me.leslie.generals.utility.Vector2D;

import java.util.List;
import java.util.Optional;

public interface MilitaryUnit {
    int getCurrentHealth();

    int getMaxHealth();

    Optional<List<MilitaryUnit>> getChildren();

    long getID();

    Vector2D getPos();
}
