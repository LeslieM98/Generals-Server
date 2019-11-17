package me.leslie.generals.core.domainevent;


import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NonNull;
import lombok.ToString;
import me.leslie.generals.core.Vector2D;
import me.leslie.generals.core.entity.interfaces.ITroop;

@Getter
@ToString
@EqualsAndHashCode
public class Movement extends DomainEvent {
    @NonNull
    private final ITroop troop;
    @NonNull
    private final Vector2D newPosition;

    public Movement(ITroop troop, Vector2D newPosition, int iterationID) {
        super(iterationID);
        this.troop = troop;
        this.newPosition = newPosition;
    }
}
