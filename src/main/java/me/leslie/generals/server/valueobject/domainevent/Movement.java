package me.leslie.generals.server.valueobject.domainevent;


import lombok.NonNull;
import lombok.Value;
import me.leslie.generals.server.valueobject.TroopID;
import me.leslie.generals.server.valueobject.Vector2D;

@Value
public class Movement extends DomainEvent {
    @NonNull
    private final TroopID troop;
    @NonNull
    private final Vector2D newPosition;

    public Movement(TroopID troop, Vector2D newPosition) {
        this.troop = troop;
        this.newPosition = newPosition;
    }
}
