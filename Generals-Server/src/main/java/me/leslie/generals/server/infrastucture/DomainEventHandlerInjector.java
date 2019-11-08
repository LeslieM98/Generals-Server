package me.leslie.generals.server.infrastucture;

import lombok.AllArgsConstructor;
import lombok.Getter;
import me.leslie.generals.server.domaineventhandler.DomainEventHandler;
import me.leslie.generals.server.repository.ArmyRepository;
import me.leslie.generals.server.repository.TroopRepository;
import org.jooq.lambda.Seq;
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
        // Since this stream consists of Class<? extends DomainEventHandler> Objects, and not of DomainEventHandler Objects
        //noinspection ClassGetClass
        return Seq.ofType(loadDomainEventHandlers().stream(), DomainEventHandler.class.getClass())
                .map(this::initialize)
                .collect(Collectors.toList());
    }

    private List<Class<? extends DomainEventHandler>> loadDomainEventHandlers() {
        Reflections reflections = new Reflections("me.leslie.generals.server");
        return new ArrayList<>(reflections.getSubTypesOf(DomainEventHandler.class));
    }

    private DomainEventHandler initialize(Class<? extends DomainEventHandler> clazz) {
        DomainEventHandler domainEventHandler = Reflect.onClass(clazz).create().get();
        domainEventHandler.setTroopRepository(troopRepository);
        domainEventHandler.setArmyRepository(armyRepository);
        return domainEventHandler;
    }
}
