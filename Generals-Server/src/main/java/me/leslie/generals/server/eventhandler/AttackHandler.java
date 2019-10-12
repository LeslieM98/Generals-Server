package me.leslie.generals.server.eventhandler;

import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.ToString;
import me.leslie.generals.core.entity.Troop;
import me.leslie.generals.core.event.Attack;
import me.leslie.generals.server.persistance.EventLogger;
import me.leslie.generals.server.repository.TroopRepository;

import java.sql.SQLException;

@AllArgsConstructor
@ToString
@EqualsAndHashCode
@Getter
public class AttackHandler {
    private final TroopRepository troupRepository;
    private final EventLogger logger;

    void handleAttack(Attack event) throws SQLException {
        Troop target = troupRepository.getTroop(event.getTarget().getID());
        int newCurrentHealth = target.getCurrentHealth() - event.getDamage();
        target = target.copy().currentHealth(newCurrentHealth).build();
        troupRepository.updateTroop(target);
        logger.log(event);
    }
}
