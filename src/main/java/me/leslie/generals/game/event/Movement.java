package me.leslie.generals.game.event;


import lombok.*;
import me.leslie.generals.game.entity.Troop;
import me.leslie.generals.utility.Vector2D;

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
