package me.leslie.generals.core.entity;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NonNull;
import lombok.ToString;
import me.leslie.generals.core.Color;
import me.leslie.generals.core.entity.pojos.ArmyComposition;

import java.io.Serializable;
import java.util.List;
import java.util.Objects;

@Getter
@AllArgsConstructor
@ToString
public class Team implements Serializable {

    @NonNull
    private final String name;
    @NonNull
    private final Color color;
    @NonNull
    private final List<ArmyComposition> armies;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Team team = (Team) o;
        return this.name.equals(team.name);
    }

    @Override
    public int hashCode() {
        return Objects.hash(name);
    }

}
