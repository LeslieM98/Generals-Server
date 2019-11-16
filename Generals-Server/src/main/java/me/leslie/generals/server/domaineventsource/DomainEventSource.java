package me.leslie.generals.server.domaineventsource;

import me.leslie.generals.core.domainevent.DomainEvent;

public interface DomainEventSource {
    DomainEvent getNext();
}
