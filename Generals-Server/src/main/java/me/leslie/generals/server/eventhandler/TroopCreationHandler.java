package me.leslie.generals.server.eventhandler;

import lombok.*;
import me.leslie.generals.core.event.TroopCreation;
import me.leslie.generals.server.persistence.EventLogger;

@AllArgsConstructor
@ToString
@EqualsAndHashCode
@Getter
public class TroopCreationHandler {
    @NonNull
    private final EventLogger logger;

    public void handleCreation(TroopCreation event) {
    }
}
