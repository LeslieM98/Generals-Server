package me.leslie.generals.server.valueobject;

import lombok.Value;

import java.util.Set;

@Value
public class Army {
    TroopID hq;
    Set<TroopID> troops;
}
