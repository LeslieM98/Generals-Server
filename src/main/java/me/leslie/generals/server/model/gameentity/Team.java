package me.leslie.generals.server.model.gameentity;

import lombok.Value;
import me.leslie.generals.server.valueobject.Color;
import me.leslie.generals.server.valueobject.TeamID;
import me.leslie.generals.server.valueobject.TroopID;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.Set;

@Document("Team")
@Value
public class Team {
    @Id
    TeamID id;

    Color color;
    Set<TroopID> armies;
}
