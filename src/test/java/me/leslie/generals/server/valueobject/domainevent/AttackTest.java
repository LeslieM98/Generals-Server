package me.leslie.generals.server.valueobject.domainevent;

import me.leslie.generals.server.model.Troop;
import me.leslie.generals.server.valueobject.*;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

class AttackTest {
    private Troop t1 = new Troop(new TroopID(1), 1, 1, new Vector2D(1.0, 1.0), new MovementSpeed(1.0, 1.0, 1.0), new CombatRange(1.0, 1.0), new ViewDistance(1.0, 1.0, 1.0));
    private Troop t2 = new Troop(new TroopID(2), 1, 1, new Vector2D(1.0, 1.0), new MovementSpeed(1.0, 1.0, 1.0), new CombatRange(1.0, 1.0), new ViewDistance(1.0, 1.0, 1.0));
    @Test
    void testCorrectCreation() {
        Attack a = new Attack(t1, t2, 9);
        Assertions.assertNotNull(a);
    }

    @Test
    void testSameTroops() {

        Assertions.assertThrows(IllegalStateException.class, () -> new Attack(t1, t1, 9));
    }

    @Test
    void testInvalidAttackDamage() {
        Assertions.assertThrows(IllegalStateException.class, () -> new Attack(t1, t2, 0));
        Assertions.assertThrows(IllegalStateException.class, () -> new Attack(t1, t2, -1));
    }
}
