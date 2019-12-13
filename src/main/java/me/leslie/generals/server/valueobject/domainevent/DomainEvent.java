package me.leslie.generals.server.valueobject.domainevent;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NonNull;
import lombok.ToString;

import java.util.Date;

@Getter
@EqualsAndHashCode
@ToString
public abstract class DomainEvent implements Comparable<DomainEvent> {
    @NonNull
    private final Date creationDate;

    public DomainEvent() {
        this.creationDate = new Date();
    }

    @Override
    public int compareTo(DomainEvent o) {
        return creationDate.compareTo(o.creationDate);
    }
}
