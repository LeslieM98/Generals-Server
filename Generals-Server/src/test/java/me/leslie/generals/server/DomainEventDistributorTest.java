package me.leslie.generals.server;

import me.leslie.generals.core.domainevent.DomainEvent;
import me.leslie.generals.core.domainevent.TroopCreation;
import me.leslie.generals.core.entity.interfaces.ITroop;
import me.leslie.generals.server.domaineventhandler.DomainEventHandler;
import me.leslie.generals.server.persistence.eventlogging.IEventLogger;
import org.junit.jupiter.api.Test;

import java.util.Date;
import java.util.HashMap;

import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;

public class DomainEventDistributorTest {
    @Test
    void testCorrectDistribution() {
        HashMap<Class<? extends DomainEvent>, DomainEventHandler<DomainEvent>> handlers = new HashMap<>();
        DomainEvent eventType1 = mock(DomainEvent.class);
        DomainEvent eventType2 = new TroopCreation(mock(ITroop.class), mock(Date.class));
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
}
