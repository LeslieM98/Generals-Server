package me.leslie.generals.game.entity;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NonNull;
import lombok.ToString;
import me.leslie.generals.utility.Color;

import java.io.Serializable;
import java.util.List;
import java.util.Objects;

@Getter
@AllArgsConstructor
@ToString
public class Team implements Serializable {

    private final int id;
    @NonNull
    private final String name;
    @NonNull
    private final Color color;
    @NonNull
    private final List<HQ> hqs;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Team team = (Team) o;
        return id == team.id;
    }

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }

}
