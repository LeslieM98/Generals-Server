package me.leslie.generals.server.eventhandler;

import lombok.*;
import me.leslie.generals.core.event.Movement;
import me.leslie.generals.server.persistance.EventLogger;
import me.leslie.generals.server.repository.TroopRepository;

@AllArgsConstructor
@ToString
@EqualsAndHashCode
@Getter
public class MovementHandler {
    @NonNull
    private final EventLogger logger;
    @NonNull
    private final TroopRepository troopRepository;

    public void handleMovement(Movement event) {
        troopRepository.updateTroop(event.getTroop().copy().position(event.getNewPosition()).build());
        logger.log(event);
    }
}
