package me.leslie.generals.game.eventhandler;

import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.ToString;
import me.leslie.generals.game.entity.Troup;
import me.leslie.generals.game.event.Movement;
import me.leslie.generals.game.repository.TroupRepository;
import me.leslie.generals.utility.EventLogger;

@AllArgsConstructor
@ToString
@EqualsAndHashCode
@Getter
public class MovementHandler {
    private final TroupRepository troupRepository;
    EventLogger logger;

    public void handleMovement(Movement event) {
        Troup unit = troupRepository.getTroup(event.getTroup().getID());
        troupRepository.updateTroup(unit.copy().position(event.getNewPosition()).build());
        logger.log(event);
    }
}
