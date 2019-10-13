package me.leslie.generals.server.eventhandler;

import lombok.*;
import me.leslie.generals.core.event.TroopCreation;
import me.leslie.generals.server.persistance.EventLogger;
import me.leslie.generals.server.repository.TroopRepository;

@AllArgsConstructor
@ToString
@EqualsAndHashCode
@Getter
public class TroopCreationHandler {
    @NonNull
    private final EventLogger logger;
    @NonNull
    private final TroopRepository troopRepository;

    public void handleCreation(TroopCreation event) {
        troopRepository.createTroop(event.getTroop().copy());
        logger.log(event);
    }
}
