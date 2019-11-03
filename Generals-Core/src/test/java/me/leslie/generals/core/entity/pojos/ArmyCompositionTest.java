package me.leslie.generals.core.entity.pojos;

import me.leslie.generals.core.entity.interfaces.IArmyComposition;
import me.leslie.generals.core.entity.interfaces.ITroop;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;

import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class ArmyCompositionTest {

    ITroop hq = new Troop(1, 100, 120, 120.2, 11.2, 12.0, 13.0, 6245.0, 1534.0, 1364.0, 121235.3, 125.3, 51.3);
    List<? extends ITroop> troops = List.of(new Troop(2, 1, 1240, 15320.2, 13441.2, 1762.0, 13.0, 6245.0, 1538634.0, 1364.0, 12125635.3, 125.3, 5451.3),
            new Troop(3, 16700, 15620, 12820.2, 1561.2, 15672.0, 13.0, 686245.0, 1534.0, 1364.0, 12121735.3, 12705.3, 5184.3),
            new Troop(4, 1030, 12840, 122450.2, 1164.2, 12.0, 163.0, 624575.0, 15364.0, 13674.0, 1211235.3, 125.3, 515.3),
            new Troop(5, 1010, 12560, 12620.2, 1751.2, 12.0, 13.0, 6245.0, 1751534.0, 1364.0, 12168235.3, 12557.3, 5186.3));


    @Test
    void nullHQ() {
        assertThrows(NullPointerException.class, () -> new ArmyComposition(null, troops));
    }

    @Test
    void nullSet() {
        assertThrows(NullPointerException.class, () -> new ArmyComposition(hq, null));
    }

    @Test
    void emptySet() {
        assertThrows(IllegalStateException.class, () -> new ArmyComposition(hq, Set.of()));
    }

    @Test
    void getter() {
        IArmyComposition ac = new ArmyComposition(hq, troops);
        assertTrue(ac.getTroops().containsAll(troops));
    }

    @Test
    void troopsContainHQ() {
        List<ITroop> illegalTroops = new ArrayList<>(troops);
        illegalTroops.add(hq);
        ITroop illegalHQ = new Troop(4, 1030, 12840, 122450.2, 1164.2, 12.0, 163.0, 624575.0, 15364.0, 13674.0, 1211235.3, 125.3, 515.3);


        assertThrows(IllegalStateException.class, () -> new ArmyComposition(hq, illegalTroops));
        ArmyComposition ac = new ArmyComposition(hq, troops);
        assertThrows(IllegalStateException.class, () -> ac.setTroops(illegalTroops));
        assertThrows(IllegalStateException.class, () -> ac.setHq(illegalHQ));
    }
}
