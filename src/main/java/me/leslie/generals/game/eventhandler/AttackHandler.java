package me.leslie.generals.game.eventhandler;

import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.ToString;
import me.leslie.generals.game.entity.Troop;
import me.leslie.generals.game.event.Attack;
import me.leslie.generals.game.repository.TroopRepository;
import me.leslie.generals.utility.EventLogger;

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
