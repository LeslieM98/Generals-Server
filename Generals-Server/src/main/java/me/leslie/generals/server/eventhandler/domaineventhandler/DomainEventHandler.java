package me.leslie.generals.server.eventhandler.domaineventhandler;

import lombok.Getter;
import lombok.Setter;
import me.leslie.generals.core.event.domainevent.DomainEvent;
import me.leslie.generals.server.repository.ArmyRepository;
import me.leslie.generals.server.repository.TroopRepository;

@Setter
@Getter
public abstract class DomainEventHandler<T extends DomainEvent> {

    private TroopRepository troopRepository;
    private ArmyRepository armyRepository;

    public abstract Class<T> getHandledEventType();

    public abstract void handle(T event);
}
