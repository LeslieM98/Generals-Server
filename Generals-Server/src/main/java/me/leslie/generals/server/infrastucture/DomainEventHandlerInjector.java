package me.leslie.generals.server.infrastucture;

import lombok.AllArgsConstructor;
import lombok.Getter;
import me.leslie.generals.server.domaineventhandler.DomainEventHandler;
import me.leslie.generals.server.repository.ArmyRepository;
import me.leslie.generals.server.repository.TroopRepository;
import org.joor.Reflect;
import org.reflections.Reflections;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@AllArgsConstructor
@Getter
public class DomainEventHandlerInjector {
    private final TroopRepository troopRepository;
    private final ArmyRepository armyRepository;


    public List<? extends DomainEventHandler> initializeDomainEventHandlers() {
        return loadDomainEventHandlers().stream()
                .map(this::initialize)
                .map(this::inject)
                .collect(Collectors.toList());
    }

    private List<Class<? extends DomainEventHandler>> loadDomainEventHandlers() {
        Reflections reflections = new Reflections("me.leslie.generals.server");
        return new ArrayList<>(reflections.getSubTypesOf(DomainEventHandler.class));
    }

    private DomainEventHandler initialize(Class<? extends DomainEventHandler> clazz) {
        return Reflect.onClass(clazz)
                .create()
                .get();
    }

    private DomainEventHandler inject(DomainEventHandler domainEventHandler) {
        domainEventHandler.setTroopRepository(troopRepository);
        domainEventHandler.setArmyRepository(armyRepository);
        return domainEventHandler;
    }
}
