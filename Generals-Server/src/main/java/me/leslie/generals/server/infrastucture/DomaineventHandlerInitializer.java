package me.leslie.generals.server.infrastucture;

import lombok.AllArgsConstructor;
import lombok.Getter;
import me.leslie.generals.core.domainevent.DomainEvent;
import me.leslie.generals.server.domaineventhandler.DomainEventHandler;
import me.leslie.generals.server.repository.ArmyRepository;
import me.leslie.generals.server.repository.TroopRepository;
import org.jooq.lambda.Seq;
import org.jooq.lambda.tuple.Tuple2;
import org.joor.Reflect;
import org.reflections.Reflections;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@AllArgsConstructor
@Getter
public class DomaineventHandlerInitializer {
    private final TroopRepository troopRepository;
    private final ArmyRepository armyRepository;

    public Map<Class<? extends DomainEvent>, ? extends DomainEventHandler> getInitializedDomainEventHandlers() {
        return Seq.ofType(loadDomainEventHandlers().stream(), DomainEventHandler.class.getClass())
                .map(this::initialize)
                .map(x -> new Tuple2<>(x.getHandledEventType(), x))
                .collect(Collectors.toMap(Tuple2::v1, Tuple2::v2));
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
