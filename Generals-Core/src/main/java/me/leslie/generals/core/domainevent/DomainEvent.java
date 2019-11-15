package me.leslie.generals.core.domainevent;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NonNull;

import java.util.Date;

@Getter
@EqualsAndHashCode
public abstract class DomainEvent implements Comparable<DomainEvent> {
    @NonNull
    private Date creationDate;
    private int iterationID;

    public DomainEvent(int iterationID) {
        if (iterationID < 0) {
            throw new IllegalStateException("IterationID cannot be negative");
        }
        this.creationDate = new Date();
        this.iterationID = iterationID;
    }

    @Override
    public int compareTo(DomainEvent domainEvent) {
        return iterationID - domainEvent.iterationID;
    }
}
