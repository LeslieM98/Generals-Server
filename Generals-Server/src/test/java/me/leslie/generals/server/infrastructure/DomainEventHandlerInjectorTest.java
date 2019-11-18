package me.leslie.generals.server.infrastructure;

import me.leslie.generals.server.eventhandler.domaineventhandler.DomainEventHandler;
import me.leslie.generals.server.infrastucture.DomainEventHandlerInjector;
import me.leslie.generals.server.repository.ArmyRepository;
import me.leslie.generals.server.repository.TroopRepository;
import org.junit.jupiter.api.Test;
import org.reflections.Reflections;

import java.util.Set;
import java.util.stream.Collectors;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.Mockito.mock;

class DomainEventHandlerInjectorTest {
    @Test
    void injectionTest() {
        TroopRepository troopRepository = mock(TroopRepository.class);
        ArmyRepository armyRepository = mock(ArmyRepository.class);
        Reflections reflections = new Reflections("me.leslie.generals.server");
        Set<Class<? extends DomainEventHandler>> domainEventHandlerClasses = reflections.getSubTypesOf(DomainEventHandler.class);


        DomainEventHandlerInjector domaineventHandlerInjector = new DomainEventHandlerInjector(troopRepository, armyRepository);
        var domainEventHandler = domaineventHandlerInjector.initializeDomainEventHandlers();

        assertTrue(domainEventHandler.stream().map(Object::getClass).collect(Collectors.toList()).containsAll(domainEventHandlerClasses));
        assertEquals(domainEventHandlerClasses.size(), domainEventHandler.size());

        domainEventHandler.forEach(x -> assertEquals(armyRepository, x.getArmyRepository()));
        domainEventHandler.forEach(x -> assertEquals(troopRepository, x.getTroopRepository()));
    }
}
