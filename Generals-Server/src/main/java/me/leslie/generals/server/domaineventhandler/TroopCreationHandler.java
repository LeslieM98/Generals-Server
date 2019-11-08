package me.leslie.generals.server.domaineventhandler;

import me.leslie.generals.core.domainevent.DomainEvent;
import me.leslie.generals.core.domainevent.TroopCreation;

public class TroopCreationHandler extends DomainEventHandler {

    @Override
    public Class<? extends DomainEvent> getHandledEventType() {
        return TroopCreation.class;
    }

    @Override
    public void handle(DomainEvent event) {
        throw new UnsupportedOperationException("Not implemented yet");
    }
}
