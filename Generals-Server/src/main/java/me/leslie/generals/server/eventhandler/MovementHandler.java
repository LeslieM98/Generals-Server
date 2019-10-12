package me.leslie.generals.server.eventhandler;

import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.ToString;
import me.leslie.generals.core.entity.Troop;
import me.leslie.generals.core.event.Movement;
import me.leslie.generals.server.persistance.EventLogger;
import me.leslie.generals.server.repository.TroopRepository;

@AllArgsConstructor
@ToString
@EqualsAndHashCode
@Getter
public class MovementHandler {
    private final TroopRepository troupRepository;
    EventLogger logger;

    public void handleMovement(Movement event) {
        Troop unit = troupRepository.getTroop(event.getTroup().getID());
        troupRepository.updateTroop(unit.copy().position(event.getNewPosition()).build());
        logger.log(event);
    }
}
