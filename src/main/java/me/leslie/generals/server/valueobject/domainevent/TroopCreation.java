package me.leslie.generals.server.valueobject.domainevent;


import lombok.Value;
import me.leslie.generals.server.valueobject.TroopID;

@Value
public class TroopCreation extends DomainEvent {
    private final TroopID troop;

    public TroopCreation(TroopID troop) {
        this.troop = troop;
    }
}
