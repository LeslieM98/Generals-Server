package me.leslie.generals.server.domaineventsource;

import me.leslie.generals.core.domainevent.DomainEvent;

public class DomainEventNetworkSource implements DomainEventSource {

    @Override
    public DomainEvent getNext() {
        throw new UnsupportedOperationException("Not implemented yet");
    }
}
