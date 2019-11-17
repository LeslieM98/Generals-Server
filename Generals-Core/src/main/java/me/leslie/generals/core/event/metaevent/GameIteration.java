package me.leslie.generals.core.event.metaevent;

import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.ToString;
import me.leslie.generals.core.event.domainevent.DomainEvent;

import java.io.Serializable;

@Getter
@AllArgsConstructor
@EqualsAndHashCode
@ToString
public class GameIteration extends MetaEvent implements Serializable {
    private final DomainEvent[] iterationEvents;
}
