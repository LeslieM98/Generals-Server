package me.leslie.generals.server.eventhandler;

import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.ToString;
import me.leslie.generals.core.event.Creation;
import me.leslie.generals.server.persistance.EventLogger;

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
