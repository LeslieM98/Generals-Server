package me.leslie.generals.game.event;

import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.ToString;
import me.leslie.generals.game.entity.MilitaryUnit;

@AllArgsConstructor
@Getter
@ToString
@EqualsAndHashCode
public class Creation {
    MilitaryUnit unit;
}
