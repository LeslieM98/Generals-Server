package me.leslie.generals.core.domainevent;


import lombok.*;
import me.leslie.generals.core.Vector2D;
import me.leslie.generals.core.entity.interfaces.ITroop;

@Getter
@AllArgsConstructor
@ToString
@EqualsAndHashCode
public class Movement {
    @NonNull
    private final ITroop troop;
    @NonNull
    private final Vector2D newPosition;
}
