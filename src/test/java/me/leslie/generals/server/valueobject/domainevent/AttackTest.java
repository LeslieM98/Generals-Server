package me.leslie.generals.server.valueobject.domainevent;

import me.leslie.generals.server.valueobject.TroopID;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

class AttackTest {
    private TroopID t1 = new TroopID(1);
    private TroopID t2 = new TroopID(2);
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
