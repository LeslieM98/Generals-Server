package me.leslie.generals.server.valueobject;

import me.leslie.generals.server.model.Troop;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class TroopTest {
    @Test
    void testRecieveDamage() {
        Troop subject = new Troop(
                new TroopID(1),
                new Health(10, 10),
                new Vector2D(0.0, 0.0),
                new MovementSpeed(1.0, 1.0, 1.0),
                new CombatRange(1.0, 1.0),
                new ViewDistance(1.0, 1.0, 1.0)
        );
        final int damage = 5;
        Troop result = subject.recieveDamage(damage);

        assertNotSame(subject, result);
        assertNotEquals(subject, result);

        assertEquals(subject.getId(), result.getId());
        assertEquals(subject.getPosition(), result.getPosition());
        assertEquals(subject.getMovementSpeed(), result.getMovementSpeed());
        assertEquals(subject.getCombatRange(), result.getCombatRange());
        assertEquals(subject.getViewDistance(), result.getViewDistance());

        assertNotEquals(subject.getHealth(), result.getHealth());
        assertEquals(subject.getHealth().getMaximum(), result.getHealth().getMaximum());
        assertEquals(subject.getHealth().getCurrent() - damage, result.getHealth().getCurrent());
    }

    @Test
    void testMove() {
        Troop subject = new Troop(
                new TroopID(1),
                new Health(10, 10),
                new Vector2D(0.0, 0.0),
                new MovementSpeed(1.0, 1.0, 1.0),
                new CombatRange(1.0, 1.0),
                new ViewDistance(1.0, 1.0, 1.0)
        );
        final Vector2D updatedPosition = new Vector2D(1.0, 1.0);
        Troop result = subject.move(updatedPosition);

        assertNotSame(subject, result);
        assertNotEquals(subject, result);

        assertEquals(subject.getId(), result.getId());
        assertEquals(subject.getHealth(), result.getHealth());
        assertEquals(subject.getMovementSpeed(), result.getMovementSpeed());
        assertEquals(subject.getCombatRange(), result.getCombatRange());
        assertEquals(subject.getViewDistance(), result.getViewDistance());

        assertNotEquals(subject.getPosition(), result.getPosition());
        assertEquals(updatedPosition, result.getPosition());
    }
}
