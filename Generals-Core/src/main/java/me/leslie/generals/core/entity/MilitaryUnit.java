package me.leslie.generals.core.entity;

import me.leslie.generals.core.Vector2D;

import java.util.List;
import java.util.Optional;

public interface MilitaryUnit {
    int getCurrentHealth();

    int getMaxHealth();

    Optional<List<MilitaryUnit>> getChildren();

    long getID();

    Vector2D getPos();
}
