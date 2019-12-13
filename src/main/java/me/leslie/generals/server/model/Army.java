package me.leslie.generals.server.model;

import lombok.Value;
import me.leslie.generals.server.valueobject.TroopID;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.Set;

@Value
@Document("Army")
public class Army {
    @Id
    TroopID hq;
    Set<TroopID> troops;
}
