package me.leslie.generals.core.domainevent;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.ToString;
import me.leslie.generals.core.entity.interfaces.ITroop;

@Getter
@ToString
@EqualsAndHashCode
public class TroopCreation extends DomainEvent {
    private final ITroop troop;

    public TroopCreation(ITroop troop, int iterationID) {
        super(iterationID);
        this.troop = troop;
    }
}