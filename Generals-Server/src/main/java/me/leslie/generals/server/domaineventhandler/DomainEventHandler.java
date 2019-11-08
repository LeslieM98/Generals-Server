package me.leslie.generals.server.domaineventhandler;

import lombok.Getter;
import lombok.Setter;
import me.leslie.generals.core.domainevent.DomainEvent;
import me.leslie.generals.server.repository.ArmyRepository;
import me.leslie.generals.server.repository.TroopRepository;

@Getter
@Setter
public abstract class DomainEventHandler {

    private TroopRepository troopRepository;
    private ArmyRepository armyRepository;

    public abstract Class<? extends DomainEvent> getHandledEventType();

    public abstract void handle(DomainEvent event);
}
