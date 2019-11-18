package me.leslie.generals.server;

import lombok.AllArgsConstructor;
import me.leslie.generals.core.event.metaevent.MetaEvent;
import me.leslie.generals.server.eventhandler.metaeventhandler.MetaEventHandler;
import me.leslie.generals.server.infrastucture.MetaEventHandlerInjector;

import java.util.Map;
import java.util.stream.Collectors;

@AllArgsConstructor
public class MetaEventDistributor {
    private Map<Class<? extends MetaEvent>, MetaEventHandler<MetaEvent>> handlers;

    public void distribute(MetaEvent metaEvent) {
        var handler = handlers.get(metaEvent.getClass());
        handler.handle(metaEvent);
    }

    public static MetaEventDistributor create() {
        Map<Class<? extends MetaEvent>, MetaEventHandler<MetaEvent>> handlers = new MetaEventHandlerInjector()
                .initializeMetaEventHandlers()
                .stream()
                .collect(
                        Collectors.toUnmodifiableMap(
                                MetaEventHandler::getHandledEventType,
                                x -> x
                        )
                );
        return new MetaEventDistributor(handlers);
    }
}
