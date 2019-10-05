package me.leslie.generals.game.eventhandler;

import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.ToString;
import me.leslie.generals.game.entity.Troup;
import me.leslie.generals.game.event.Attack;
import me.leslie.generals.game.repository.TroupRepository;
import me.leslie.generals.utility.EventLogger;

@AllArgsConstructor
@ToString
@EqualsAndHashCode
@Getter
public class AttackHandler {
    private final TroupRepository troupRepository;
    private final EventLogger logger;

    void handleAttack(Attack event) {
        Troup target = troupRepository.getTroup(event.getTarget().getID());
        int newCurrentHealth = target.getCurrentHealth() - event.getDamage();
        target = target.copy().currentHealth(newCurrentHealth).build();
        troupRepository.updateTroup(target);
        logger.log(event);
    }
}
