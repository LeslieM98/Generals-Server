package me.leslie.generals.server.persistence.eventlogging;

import lombok.AllArgsConstructor;
import me.leslie.generals.core.domainevent.DomainEvent;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Deque;
import java.util.List;

@AllArgsConstructor
public class BasicEventLogger implements Serializable, IEventLogger {

    private final Deque<DomainEvent> events;

    @Override
    public void log(DomainEvent event) {
        events.push(event);
    }

    public List<DomainEvent> getEventsAsList() {
        return List.copyOf(new ArrayList<>(events));
    }
}
