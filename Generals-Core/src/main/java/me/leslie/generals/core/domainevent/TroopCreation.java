package me.leslie.generals.core.domainevent;

import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.ToString;
import me.leslie.generals.core.entity.interfaces.ITroop;

@AllArgsConstructor
@Getter
@ToString
@EqualsAndHashCode
public class TroopCreation {
    ITroop troop;
}
