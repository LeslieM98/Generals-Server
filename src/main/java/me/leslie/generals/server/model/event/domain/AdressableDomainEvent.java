package me.leslie.generals.server.model.event.domain;

import lombok.*;
import me.leslie.generals.server.valueobject.event.domain.IDomainEvent;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import javax.validation.constraints.NotNull;
import java.util.Date;

@Getter
@Setter
@EqualsAndHashCode
@ToString
@NoArgsConstructor
@Document("AdressableDomainEvent")
public class AdressableDomainEvent implements Comparable<AdressableDomainEvent> {

    @Id
    private int id;
    private Date creationDate;
    private IDomainEvent event;


    public AdressableDomainEvent(int id, @NotNull IDomainEvent event) {
        this(id, new Date(), event);
    }

    public AdressableDomainEvent(int id, @NotNull Date creationDate, @NotNull IDomainEvent event) {
        this.id = id;
        this.creationDate = creationDate;
        this.event = event;
    }

    @Override
    public int compareTo(AdressableDomainEvent other) {
        return Integer.compare(id, other.getId());
    }
}
