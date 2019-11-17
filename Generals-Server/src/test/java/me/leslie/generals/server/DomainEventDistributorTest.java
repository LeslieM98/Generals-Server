package me.leslie.generals.server;

import me.leslie.generals.core.event.domainevent.DomainEvent;
import me.leslie.generals.core.event.domainevent.TroopCreation;
import me.leslie.generals.core.entity.interfaces.ITroop;
import me.leslie.generals.server.domaineventhandler.DomainEventHandler;
import me.leslie.generals.server.persistence.eventlogging.IEventLogger;
import me.leslie.generals.server.repository.ArmyRepository;
import me.leslie.generals.server.repository.TroopRepository;
import org.junit.jupiter.api.Test;
import org.reflections.Reflections;

import java.util.HashMap;
import java.util.stream.Collectors;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;

public class DomainEventDistributorTest {
    @Test
    void testCorrectDistribution() {
        HashMap<Class<? extends DomainEvent>, DomainEventHandler<DomainEvent>> handlers = new HashMap<>();
        DomainEvent eventType1 = mock(DomainEvent.class);
        DomainEvent eventType2 = new TroopCreation(mock(ITroop.class));
        DomainEventHandler<DomainEvent> handler1 = mock(DomainEventHandler.class);
        DomainEventHandler<DomainEvent> handler2 = mock(DomainEventHandler.class);


        handlers.put(eventType1.getClass(), handler1);
        handlers.put(eventType2.getClass(), handler2);

        DomainEventDistributor distributor = new DomainEventDistributor(handlers, mock(IEventLogger.class));

        distributor.distribute(eventType1);
        verify(handler1).handle(eventType1);

        distributor.distribute(eventType2);
        verify(handler2).handle(eventType2);
    }

    @Test
    void factoryContainsAllClasses() {
        TroopRepository troopRepositoryMock = mock(TroopRepository.class);
        ArmyRepository armyRepositoryMock = mock(ArmyRepository.class);
        DomainEventDistributor subject = DomainEventDistributor.create(troopRepositoryMock, armyRepositoryMock, mock(IEventLogger.class));

        Reflections reflections = new Reflections("me.leslie.generals.server");
        var types = reflections.getSubTypesOf(DomainEventHandler.class);

        var loadedDomainEventHandlers = subject
                .getHandlers()
                .values();

        assertEquals(types.size(), loadedDomainEventHandlers.size());

        var loadedDomainEventHandlerNames = loadedDomainEventHandlers.stream().map(x -> x.getClass().getName()).collect(Collectors.toList());
        types.forEach(x -> assertTrue(loadedDomainEventHandlerNames.contains(x.getName())));

        loadedDomainEventHandlers.forEach(x -> assertEquals(troopRepositoryMock, x.getTroopRepository()));

        loadedDomainEventHandlers.forEach(x -> assertEquals(armyRepositoryMock, x.getArmyRepository()));

        subject.getHandlers().forEach((x, y) -> assertEquals(x, y.getHandledEventType()));
    }
}
