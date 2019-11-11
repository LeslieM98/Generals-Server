package me.leslie.generals.server.persistence.eventlogging;

import me.leslie.generals.core.domainevent.DomainEvent;

public interface IEventLogger {
    void log(DomainEvent event);
}
