package me.leslie.generals.server.eventhandler.metaeventhandler;

import me.leslie.generals.core.event.metaevent.MetaEvent;

public abstract class MetaEventHandler<T extends MetaEvent> {
    public abstract Class<T> getHandledEventType();

    public abstract void handle(T event);
}
