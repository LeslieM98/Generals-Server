package me.leslie.generals.server.valueobject;

import lombok.Value;

@Value
public final class Vector2D {
    double x;
    double y;

    public double calculateLength() {
        return Math.sqrt(x * x + y * y);
    }
}
