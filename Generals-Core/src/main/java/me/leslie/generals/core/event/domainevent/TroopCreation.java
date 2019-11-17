package me.leslie.generals.core.event.domainevent;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.ToString;
import me.leslie.generals.core.entity.interfaces.ITroop;

@Getter
@ToString
@EqualsAndHashCode
public class TroopCreation extends DomainEvent {
    private final ITroop troop;

    public TroopCreation(ITroop troop) {
        this.troop = troop;
    }
}
