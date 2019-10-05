package me.leslie.generals.game.eventhandler;

import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.ToString;
import me.leslie.generals.game.event.Creation;
import me.leslie.generals.utility.EventLogger;

@AllArgsConstructor
@ToString
@EqualsAndHashCode
@Getter
public class CreationHandler {
    private final EventLogger logger;

    public void handleCreation(Creation event) {
        logger.log(event);
    }
}
