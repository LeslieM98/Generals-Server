package me.leslie.generals.core;

import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.ToString;

import java.io.Serializable;


@Getter
@EqualsAndHashCode
@ToString
@AllArgsConstructor
public class Vector2D implements Serializable {
    private final double x;
    private final double y;

    public double getLength() {
        return Math.sqrt(x * x + y * y);
    }
}
