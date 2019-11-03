package me.leslie.generals.server.eventhandler;

import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.ToString;
import me.leslie.generals.core.event.Attack;
import me.leslie.generals.server.persistence.EventLogger;

@ToString
@EqualsAndHashCode
@Getter
@AllArgsConstructor
public class AttackHandler {
    EventLogger eventLogger;

    void handleAttack(Attack event) {

    }
}
