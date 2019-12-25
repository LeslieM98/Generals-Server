package me.leslie.generals.server.valueobject.event.domain;


import lombok.NonNull;
import lombok.Value;
import me.leslie.generals.server.valueobject.TroopID;
import me.leslie.generals.server.valueobject.Vector2D;

@Value
public class Movement implements DomainEvent {
    @NonNull
    TroopID troop;
    @NonNull
    Vector2D newPosition;
}
