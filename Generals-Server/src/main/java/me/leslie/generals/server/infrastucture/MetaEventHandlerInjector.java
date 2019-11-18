package me.leslie.generals.server.infrastucture;

import me.leslie.generals.server.eventhandler.metaeventhandler.MetaEventHandler;
import org.joor.Reflect;
import org.reflections.Reflections;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class MetaEventHandlerInjector {
    public List<? extends MetaEventHandler> initializeMetaEventHandlers() {
        return loadDomainEventHandlers().stream()
                .map(this::initialize)
                .collect(Collectors.toList());
    }

    private List<Class<? extends MetaEventHandler>> loadDomainEventHandlers() {
        Reflections reflections = new Reflections("me.leslie.generals.server");
        return new ArrayList<>(reflections.getSubTypesOf(MetaEventHandler.class));
    }

    private MetaEventHandler initialize(Class<? extends MetaEventHandler> clazz) {
        return Reflect.onClass(clazz)
                .create()
                .get();
    }
}
