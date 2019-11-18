package me.leslie.generals.server.eventhandler.domaineventhandler;

import me.leslie.generals.core.event.domainevent.TroopCreation;

public class TroopCreationHandler extends DomainEventHandler<TroopCreation> {

    @Override
    public Class<TroopCreation> getHandledEventType() {
        return TroopCreation.class;
    }

    @Override
    public void handle(TroopCreation event) {
        throw new UnsupportedOperationException("Not implemented yet");
    }
}
