package me.leslie.generals.server.persistance;

import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.ToString;
import me.leslie.generals.core.event.Attack;
import me.leslie.generals.core.event.Movement;
import me.leslie.generals.core.event.TroopCreation;

import java.util.Map;

@AllArgsConstructor
@Getter
@EqualsAndHashCode
@ToString
public class EventLogger {
    private final Map<Long, Movement> movements;
    private final Map<Long, Attack> attacks;
    private final Map<Long, TroopCreation> creations;
    private long nextID;

    private long nextID() {
        return nextID++;
    }

    public void log(Movement event) {
        movements.put(nextID(), event);
    }

    public void log(Attack event) {
        attacks.put(nextID(), event);
    }

    public void log(TroopCreation event) {
        creations.put(nextID(), event);
    }
}
