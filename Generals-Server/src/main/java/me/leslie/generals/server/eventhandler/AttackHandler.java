package me.leslie.generals.server.eventhandler;

import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.ToString;
import me.leslie.generals.core.event.Attack;
import me.leslie.generals.server.persistence.EventLogger;
import me.leslie.generals.server.repository.TroopRepository;

@ToString
@EqualsAndHashCode
@Getter
@AllArgsConstructor
public class AttackHandler {
    private final EventLogger logger;
    private final TroopRepository troopRepository;

    void handleAttack(Attack event) {
        int updatedHealth = event.getTarget().getCurrentHealth() - event.getDamage();
        troopRepository.updateTroop(event.getTarget().copy().currentHealth(updatedHealth).build());
        logger.log(event);
    }
}
