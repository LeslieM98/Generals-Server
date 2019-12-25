package me.leslie.generals.server.repository.event.domain;

import me.leslie.generals.server.model.event.domain.AdressableDomainEvent;
import org.springframework.data.repository.CrudRepository;

public interface DomainEventRepository extends CrudRepository<AdressableDomainEvent, Integer> {

}
