package me.leslie.generals.game.eventhandler;

import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.ToString;
import me.leslie.generals.game.entity.Troop;
import me.leslie.generals.game.event.Movement;
import me.leslie.generals.game.repository.TroopRepository;
import me.leslie.generals.utility.EventLogger;

import java.sql.SQLException;

@AllArgsConstructor
@ToString
@EqualsAndHashCode
@Getter
public class MovementHandler {
    private final TroopRepository troupRepository;
    EventLogger logger;

    public void handleMovement(Movement event) throws SQLException {
        Troop unit = troupRepository.getTroop(event.getTroup().getID());
        troupRepository.updateTroop(unit.copy().position(event.getNewPosition()).build());
        logger.log(event);
    }
}
