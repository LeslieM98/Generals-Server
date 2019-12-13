package me.leslie.generals.server.valueobject.domainevent;


import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.ToString;
import me.leslie.generals.server.model.Troop;

@Getter
@ToString
@EqualsAndHashCode
public class TroopCreation extends DomainEvent {
    private final Troop troop;

    public TroopCreation(Troop troop) {
        this.troop = troop;
    }
}
