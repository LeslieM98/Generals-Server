package me.leslie.generals.game.entity;

import lombok.Getter;
import lombok.ToString;
import me.leslie.generals.utility.Vector2D;

import java.io.Serializable;
import java.util.Collections;
import java.util.List;
import java.util.Objects;
import java.util.Optional;

@Getter
@ToString
public class Army implements MilitaryUnit, Serializable {
    final List<MilitaryUnit> units;
    private final int id;

    public Army(List<MilitaryUnit> units, int id) {
        this.units = Objects.requireNonNullElse(units, Collections.emptyList());
        this.id = id;
    }

    @Override
    public int getCurrentHealth() {
        return units.stream().map(MilitaryUnit::getCurrentHealth).reduce(0, Integer::sum);
    }

    @Override
    public int getMaxHealth() {
        return units.stream().map(MilitaryUnit::getMaxHealth).reduce(0, Integer::sum);
    }

    @Override
    public Optional<List<MilitaryUnit>> getChildren() {
        return Optional.of(Collections.unmodifiableList(units));
    }

    @Override
    public int getID() {
        return id;
    }

    @Override
    public Vector2D getPos() {
        double newX = units.stream().map(unit -> unit.getPos().getX()).reduce(0.0, Double::sum);
        double newY = units.stream().map(unit -> unit.getPos().getY()).reduce(0.0, Double::sum);

        return new Vector2D(newX, newY);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Army army = (Army) o;
        return id == army.id;
    }

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }
}
