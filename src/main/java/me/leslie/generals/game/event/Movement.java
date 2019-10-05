package me.leslie.generals.game.event;


import lombok.*;
import me.leslie.generals.game.entity.Troup;
import me.leslie.generals.utility.Vector2D;

@Getter
@AllArgsConstructor
@ToString
@EqualsAndHashCode
public class Movement {
    @NonNull
    private final Troup troup;
    @NonNull
    private final Vector2D newPosition;
}
