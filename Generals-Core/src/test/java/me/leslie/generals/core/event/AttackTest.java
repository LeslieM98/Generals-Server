package me.leslie.generals.core.event;

import me.leslie.generals.core.entity.Troop;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.mock;

class AttackTest {
    @Test
    void testCorrectCreation() {
        Troop t1 = mock(Troop.class);
        Troop t2 = mock(Troop.class);
        Attack a = new Attack(t1, t2, 9);
        assertNotNull(a);
    }

    @Test
    void testSameTroops() {
        Troop t1 = mock(Troop.class);
        assertThrows(IllegalStateException.class, () -> new Attack(t1, t1, 9));
    }

    @Test
    void testInvalidAttackDamage() {
        Troop t1 = mock(Troop.class);
        Troop t2 = mock(Troop.class);
        assertThrows(IllegalStateException.class, () -> new Attack(t1, t2, 0));
        assertThrows(IllegalStateException.class, () -> new Attack(t1, t2, -1));
    }
}
