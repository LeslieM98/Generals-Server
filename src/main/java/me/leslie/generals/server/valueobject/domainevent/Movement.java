package me.leslie.generals.server.valueobject.domainevent;


import lombok.NonNull;
import lombok.Value;
import me.leslie.generals.server.model.Troop;
import me.leslie.generals.server.valueobject.Vector2D;

@Value
public class Movement extends DomainEvent {
    @NonNull
    private final Troop troop;
    @NonNull
    private final Vector2D newPosition;

    public Movement(Troop troop, Vector2D newPosition) {
        this.troop = troop;
        this.newPosition = newPosition;
    }
}
