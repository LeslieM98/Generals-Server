package me.leslie.generals.server.eventhandler;

import lombok.*;
import me.leslie.generals.core.event.Movement;
import me.leslie.generals.server.persistence.EventLogger;

@AllArgsConstructor
@ToString
@EqualsAndHashCode
@Getter
public class MovementHandler {
    @NonNull
    private final EventLogger logger;

    public void handleMovement(Movement event) {
    }
}
