package me.leslie.generals.server.model.event.domain;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NonNull;
import lombok.ToString;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.Date;

@Getter
@EqualsAndHashCode
@ToString
@Document("DomainEvent")
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
