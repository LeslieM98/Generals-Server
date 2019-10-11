package me.leslie.generals.core.event;


import lombok.*;
import me.leslie.generals.core.Vector2D;
import me.leslie.generals.core.entity.Troop;

@Getter
@AllArgsConstructor
@ToString
@EqualsAndHashCode
public class Movement {
    @NonNull
    private final Troop troup;
    @NonNull
    private final Vector2D newPosition;
}
