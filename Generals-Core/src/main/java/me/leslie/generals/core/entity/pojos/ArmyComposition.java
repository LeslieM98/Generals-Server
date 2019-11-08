package me.leslie.generals.core.entity.pojos;

import lombok.NonNull;
import lombok.ToString;
import me.leslie.generals.core.entity.interfaces.IArmyComposition;
import me.leslie.generals.core.entity.interfaces.ITroop;

import java.io.Serializable;
import java.util.*;
import java.util.stream.Collectors;

@ToString
public class ArmyComposition implements IArmyComposition, Serializable {
    private ITroop hq;
    private List<? extends ITroop> troops;

    public ArmyComposition(@NonNull ITroop hq, @NonNull Collection<? extends ITroop> troops) {
        validate(hq, troops);
        this.hq = new Troop(hq);
        this.troops = new ArrayList<>(troops);
    }

    public ArmyComposition(@NonNull IArmyComposition other) {
        this(other.getHQ(), other.getTroops());
    }

    private void validate(ITroop hq, Collection<? extends ITroop> troops) {
        if (troops.isEmpty()) {
            throw new IllegalStateException("Troops are empty");
        }
        if (troops.contains(hq)) {
            throw new IllegalStateException("Troops containing HQ");
        }
    }

    @Override
    public ITroop getHQ() {
        return hq;
    }

    @Override
    public List<? extends ITroop> getTroops() {
        return Collections.unmodifiableList(troops);
    }

    public void setHq(@NonNull ITroop hq) {
        validate(hq, troops);
        this.hq = hq;
    }

    public void setTroops(@NonNull Collection<? extends ITroop> troops) {
        validate(hq, troops);
        this.troops = new ArrayList<>(troops);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        ArmyComposition that = (ArmyComposition) o;
        return hq.equals(that.hq) &&
                troops.size() == that.troops.size() &&
                troops.containsAll(that.troops);
    }

    @Override
    public int hashCode() {
        return Objects.hash(hq, troops.stream().sorted(Comparator.comparingInt(x -> x.getId())).collect(Collectors.toList()));
    }
}
