package me.leslie.generals.core.event;

import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.ToString;
import me.leslie.generals.core.entity.Troop;

@AllArgsConstructor
@Getter
@ToString
@EqualsAndHashCode
public class TroopCreation {
    Troop troop;
}
