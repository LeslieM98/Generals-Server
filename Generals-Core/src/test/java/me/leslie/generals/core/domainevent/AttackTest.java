package me.leslie.generals.core.domainevent;

import me.leslie.generals.core.entity.interfaces.ITroop;
import me.leslie.generals.core.entity.pojos.Troop;
import org.junit.jupiter.api.Test;

import java.util.Date;

import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.mock;

class AttackTest {
    @Test
    void testCorrectCreation() {
        ITroop t1 = mock(Troop.class);
        ITroop t2 = mock(Troop.class);
        Attack a = new Attack(t1, t2, 9, new Date());
        assertNotNull(a);
    }

    @Test
    void testSameTroops() {
        ITroop t1 = mock(Troop.class);
        assertThrows(IllegalStateException.class, () -> new Attack(t1, t1, 9, new Date()));
    }

    @Test
    void testInvalidAttackDamage() {
        ITroop t1 = mock(Troop.class);
        ITroop t2 = mock(Troop.class);
        assertThrows(IllegalStateException.class, () -> new Attack(t1, t2, 0, new Date()));
        assertThrows(IllegalStateException.class, () -> new Attack(t1, t2, -1, new Date()));
    }
}
