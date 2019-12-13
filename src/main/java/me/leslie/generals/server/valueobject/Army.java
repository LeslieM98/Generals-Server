package me.leslie.generals.server.valueobject;

import lombok.Value;
import me.leslie.generals.server.model.Troop;

import java.util.Set;

@Value
public class Army {
    Troop hq;
    Set<Troop> troops;
}
