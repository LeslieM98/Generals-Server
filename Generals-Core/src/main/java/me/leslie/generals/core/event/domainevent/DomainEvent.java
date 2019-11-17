package me.leslie.generals.core.event.domainevent;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NonNull;

import java.io.Serializable;
import java.util.Date;

@Getter
@EqualsAndHashCode
public abstract class DomainEvent implements Comparable<DomainEvent>, Serializable {
    @NonNull
    private Date creationDate;

    public DomainEvent() {
        this.creationDate = new Date();
    }

    @Override
    public int compareTo(DomainEvent o) {
        return creationDate.compareTo(o.creationDate);
    }
}
