package me.leslie.generals.utility;

import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.ToString;

import java.io.Serializable;

@Getter
@AllArgsConstructor
@EqualsAndHashCode
@ToString
public class AttackDistance implements Serializable {
    private final double closeCombat;
    private final double rangedCombat;
}
