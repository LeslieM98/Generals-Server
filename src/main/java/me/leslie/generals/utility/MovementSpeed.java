package me.leslie.generals.utility;


import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.ToString;

import java.io.Serializable;

@Getter
@AllArgsConstructor
@ToString
@EqualsAndHashCode
public class MovementSpeed implements Serializable {
    private final double normal;
    private final double street;
    private final double difficultTerrain;
}
