package me.leslie.generals.core.domainevent;


import lombok.*;
import me.leslie.generals.core.Vector2D;
import me.leslie.generals.core.entity.interfaces.ITroop;

import java.util.Date;

@Getter
@AllArgsConstructor
@ToString
@EqualsAndHashCode
public class Movement implements DomainEvent {
    @NonNull
    private final ITroop troop;
    @NonNull
    private final Vector2D newPosition;
    @NonNull
    private final Date creationDate;
}
