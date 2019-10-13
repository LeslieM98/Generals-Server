package me.leslie.generals.core.entity;

import lombok.Builder;
import lombok.Getter;
import lombok.NonNull;
import lombok.ToString;

import java.util.List;
import java.util.Objects;

@Getter
@ToString
@Builder
public class Army {
    @NonNull
    private final Troop hq;
    @NonNull
    private final List<Troop> troops;

    public Army(Troop hq, List<Troop> troops) {
        if (troops.isEmpty()) {
            throw new IllegalStateException("troops cannot be empty");
        }
        this.hq = hq;
        this.troops = troops;
    }

    public ArmyBuilder copy() {
        return builder().hq(hq).troops(troops);
    }

    public long getID() {
        return hq.getId();
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Army army = (Army) o;
        return getID() == army.getID();
    }

    @Override
    public int hashCode() {
        return Objects.hash(hq.hashCode());
    }

}
