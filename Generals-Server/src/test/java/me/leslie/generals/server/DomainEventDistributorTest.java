package me.leslie.generals.server;

import me.leslie.generals.core.domainevent.DomainEvent;
import me.leslie.generals.server.domaineventhandler.DomainEventHandler;
import me.leslie.generals.server.persistence.IEventLogger;
import org.junit.jupiter.api.Test;

import static org.mockito.Mockito.mock;

import java.util.HashMap;

public class DomainEventDistributorTest {
    @Test
    void testCorrectDistribution() {
        HashMap<Class<? extends DomainEvent>, ? extends DomainEventHandler<?>> handlers = new HashMap<>();
        DomainEvent eventType1 = mock(DomainEvent.class);
        DomainEvent eventType2 = mock(DomainEvent.class);

        DomainEventHandler<?> handler1 = mock(DomainEventHandler.class);
        DomainEventHandler<?> handler2 = mock(DomainEventHandler.class);

        handlers.put(eventType1.getClass(), handler1);
        handlers.put(eventType2.getClass(), handler2);

        DomainEventDistributor distributor = new DomainEventDistributor(handlers, mock(IEventLogger.class));

    }
}
