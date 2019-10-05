package me.leslie.generals.utility;

import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.ToString;
import me.leslie.generals.game.event.Attack;
import me.leslie.generals.game.event.Creation;
import me.leslie.generals.game.event.Movement;

import java.util.Map;

@AllArgsConstructor
@Getter
@EqualsAndHashCode
@ToString
public class EventLogger {
    private final Map<Long, Movement> movements;
    private final Map<Long, Attack> attacks;
    private final Map<Long, Creation> creations;
    private long eventId;

    private long nextID() {
        return eventId++;
    }

    public void log(Movement event) {
        movements.put(nextID(), event);
    }

    public void log(Attack event) {
        attacks.put(nextID(), event);
    }

    public void log(Creation event) {
        creations.put(nextID(), event);
    }
}
