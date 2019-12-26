package me.leslie.generals.server.repository.event.domain;

import me.leslie.generals.server.model.event.domain.AdressableDomainEvent;
import org.springframework.data.repository.CrudRepository;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

public interface IDomainEventStore extends CrudRepository<AdressableDomainEvent, Integer> {
    default Integer findNextID() {
        List<AdressableDomainEvent> events = new ArrayList<>();
        findAll().forEach(events::add);

        return events.stream()
                .map(AdressableDomainEvent::getId)
                .sorted()
                .max(Comparator.comparingInt(x -> x)).orElseThrow(() -> new IllegalStateException("Could not fetch new ID"));
    }
}
