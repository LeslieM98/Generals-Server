package me.leslie.generals.core.entity.pojos;

import lombok.EqualsAndHashCode;
import lombok.NonNull;
import me.leslie.generals.core.entity.interfaces.IArmyComposition;
import me.leslie.generals.core.entity.interfaces.ITroop;

import java.io.Serializable;
import java.util.Collection;
import java.util.Collections;
import java.util.HashSet;
import java.util.Set;


@EqualsAndHashCode
public class ArmyComposition implements IArmyComposition, Serializable {
    private ITroop hq;
    private Set<? extends ITroop> troops;

    public ArmyComposition(@NonNull ITroop hq, @NonNull Collection<? extends ITroop> troops) {
        validate(hq, troops);
        this.hq = hq;
        this.troops = new HashSet<>(troops);
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
    public Set<? extends ITroop> getTroops() {
        return Collections.unmodifiableSet(troops);
    }

    public void setHq(@NonNull ITroop hq) {
        validate(hq, troops);
        this.hq = hq;
    }

    public void setTroops(@NonNull Collection<? extends ITroop> troops) {
        validate(hq, troops);
        this.troops = new HashSet<>(troops);
    }
}
