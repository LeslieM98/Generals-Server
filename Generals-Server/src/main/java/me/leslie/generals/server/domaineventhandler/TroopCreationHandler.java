package me.leslie.generals.server.domaineventhandler;

import me.leslie.generals.core.domainevent.TroopCreation;

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
