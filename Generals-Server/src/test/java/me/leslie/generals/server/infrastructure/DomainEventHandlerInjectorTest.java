package me.leslie.generals.server.infrastructure;

import me.leslie.generals.server.domaineventhandler.DomainEventHandler;
import me.leslie.generals.server.infrastucture.DomaineventHandlerInjector;
import me.leslie.generals.server.repository.ArmyRepository;
import me.leslie.generals.server.repository.TroopRepository;
import org.junit.jupiter.api.Test;
import org.reflections.Reflections;

import java.util.Set;
import java.util.stream.Collectors;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.mock;

public class DomainEventHandlerInjectorTest {
    @Test
    void injectionTest() {
        TroopRepository troopRepository = mock(TroopRepository.class);
        ArmyRepository armyRepository = mock(ArmyRepository.class);
        Reflections reflections = new Reflections("me.leslie.generals.server");
        Set<Class<? extends DomainEventHandler>> domainEventHandlerClasses = reflections.getSubTypesOf(DomainEventHandler.class);


        DomaineventHandlerInjector domaineventHandlerInjector = new DomaineventHandlerInjector(troopRepository, armyRepository);
        var domaineventHandler = domaineventHandlerInjector.initializeDomaineventHandlers().entrySet();

        assertTrue(domaineventHandler.stream().map(x -> x.getValue().getClass()).collect(Collectors.toSet()).containsAll(domainEventHandlerClasses));
        assertEquals(domainEventHandlerClasses.size(), domaineventHandler.size());
        assertFalse(domaineventHandler.stream().map(x -> x.getValue().getClass()).collect(Collectors.toSet()).contains(DomainEventHandler.class));

        domaineventHandler.forEach(x -> assertEquals(x.getValue().getHandledEventType(), x.getKey()));
        domaineventHandler.forEach(x -> assertEquals(armyRepository, x.getValue().getArmyRepository()));
        domaineventHandler.forEach(x -> assertEquals(troopRepository, x.getValue().getTroopRepository()));


    }
}
