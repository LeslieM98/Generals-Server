package me.leslie.generals.server;

import lombok.AllArgsConstructor;
import lombok.Getter;
import me.leslie.generals.core.domainevent.DomainEvent;
import me.leslie.generals.server.domaineventhandler.DomainEventHandler;
import me.leslie.generals.server.infrastucture.DomainEventHandlerInjector;
import me.leslie.generals.server.persistence.eventlogging.IEventLogger;
import me.leslie.generals.server.repository.ArmyRepository;
import me.leslie.generals.server.repository.TroopRepository;

import java.util.Map;
import java.util.stream.Collectors;

@AllArgsConstructor
@Getter
public class DomainEventDistributor {
    private Map<Class<? extends DomainEvent>, DomainEventHandler<DomainEvent>> handlers;
    private IEventLogger eventLogger;

    public void distribute(DomainEvent domainEvent){
        var handler = handlers.get(domainEvent.getClass());
        handler.handle(domainEvent);
    }

    public static DomainEventDistributor create(TroopRepository troopRepository, ArmyRepository armyRepository){
        var handlers = new DomainEventHandlerInjector(troopRepository, armyRepository).initializeDomainEventHandlers().stream().collect(Collectors.toUnmodifiableMap(DomainEventHandler::getHandledEventType, x -> x));
        throw new UnsupportedOperationException("Not implemented yet");
    }
}
