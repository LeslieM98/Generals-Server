package me.leslie.generals.server.persistence;

import me.leslie.generals.core.domainevent.DomainEvent;

public interface IEventLogger {
    void log(DomainEvent event);
}
