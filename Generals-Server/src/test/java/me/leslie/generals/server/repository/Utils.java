package me.leslie.generals.server.repository;

import me.leslie.generals.core.CombatRange;
import me.leslie.generals.core.MovementSpeed;
import me.leslie.generals.core.Vector2D;
import me.leslie.generals.core.ViewDistance;
import me.leslie.generals.core.entity.Army;
import me.leslie.generals.core.entity.Army.ArmyBuilder;
import me.leslie.generals.core.entity.Troop;
import org.jooq.lambda.Seq;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.Objects;

public class Utils {

    private Utils() {
    }

    static boolean deepEquality(Troop expected, Troop actual) {
        if (expected == actual) {
            return true;
        }
        Objects.requireNonNull(expected, "expected is null");
        Objects.requireNonNull(actual, "actual is null");

        boolean equals = expected.getId() == actual.getId();
        equals &= expected.getCurrentHealth() == actual.getCurrentHealth();
        equals &= expected.getMaxHealth() == actual.getMaxHealth();
        equals &= expected.getPosition().equals(actual.getPosition());
        equals &= expected.getMovementSpeed().equals(actual.getMovementSpeed());
        equals &= expected.getCombatRange().equals(actual.getCombatRange());
        equals &= expected.getViewDistance().equals(actual.getViewDistance());

        return equals;
    }

    static boolean deepEquality(Army expected, Army actual) {
        if (expected == actual) {
            return true;
        }
        Objects.requireNonNull(expected, "expected is null");
        Objects.requireNonNull(actual, "actual is null");

        boolean equals = expected.getID() == actual.getID();
        equals &= deepEquality(expected.getHq(), actual.getHq());
        equals &= expected.getTroops().size() == actual.getTroops().size();
        equals &= Seq.ofType(expected.getTroops().stream().sorted(Comparator.comparingInt(x -> (int) x.getId())), Troop.class)
                .zip(actual.getTroops().stream().sorted(Comparator.comparingInt(x -> (int) x.getId())))
                .map(x -> deepEquality(x.v1(), x.v2())).reduce(true, (x, y) -> x && y);
        return equals;
    }

    static List<Troop> initializeTroops(TroopRepository repository) {
        ArrayList<Troop> troops = new ArrayList<>();
        troops.add(repository.createTroop(Troop.builder()
                .currentHealth(100)
                .maxHealth(120)
                .position(new Vector2D(120.2, 11.2))
                .movementSpeed(new MovementSpeed(12.0, 13.0, 6245.0))
                .combatRange(new CombatRange(1534.0, 1364.0))
                .viewDistance(new ViewDistance(121235.3, 125.3, 51.3))));

        troops.add(repository.createTroop(Troop.builder()
                .currentHealth(1)
                .maxHealth(1240)
                .position(new Vector2D(15320.2, 13441.2))
                .movementSpeed(new MovementSpeed(1762.0, 13.0, 6245.0))
                .combatRange(new CombatRange(1538634.0, 1364.0))
                .viewDistance(new ViewDistance(12125635.3, 125.3, 5451.3))));

        troops.add(repository.createTroop(Troop.builder()
                .currentHealth(16700)
                .maxHealth(15620)
                .position(new Vector2D(12820.2, 1561.2))
                .movementSpeed(new MovementSpeed(15672.0, 13.0, 686245.0))
                .combatRange(new CombatRange(1534.0, 1364.0))
                .viewDistance(new ViewDistance(12121735.3, 12705.3, 5184.3))));

        troops.add(repository.createTroop(Troop.builder()
                .currentHealth(1030)
                .maxHealth(12840)
                .position(new Vector2D(122450.2, 1164.2))
                .movementSpeed(new MovementSpeed(12.0, 163.0, 624575.0))
                .combatRange(new CombatRange(15364.0, 13674.0))
                .viewDistance(new ViewDistance(1211235.3, 125.3, 515.3))));

        troops.add(repository.createTroop(Troop.builder()
                .currentHealth(1010)
                .maxHealth(12560)
                .position(new Vector2D(12620.2, 1751.2))
                .movementSpeed(new MovementSpeed(12.0, 13.0, 6245.0))
                .combatRange(new CombatRange(1751534.0, 1364.0))
                .viewDistance(new ViewDistance(12168235.3, 12557.3, 5186.3))));

        return List.copyOf(troops);
    }

    static List<Army> initializeArmies(ArmyRepository repository) {
        List<Troop> troops = initializeTroops(repository.getTroopRepository());
        List<Army> armies = new ArrayList<>();
        ArmyBuilder army1 = Army.builder().hq(troops.get(0)).troops(List.of(troops.get(1)));
        ArmyBuilder army2 = Army.builder().hq(troops.get(2)).troops(List.of(troops.get(3), troops.get(4)));
        armies.add(repository.createArmy(army1));
        armies.add(repository.createArmy(army2));
        return armies;
    }
}
