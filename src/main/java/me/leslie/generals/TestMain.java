package me.leslie.generals;

import me.leslie.generals.game.entity.Troop;
import me.leslie.generals.game.entity.Troop.TroopBuilder;
import me.leslie.generals.game.repository.TroopRepository;
import me.leslie.generals.utility.*;

public class TestMain {
    public static void main(String[] args) {
        try {
            DataBase db = DataBase.get();
            TroopRepository troops = new TroopRepository(db);
            TroopBuilder tb = Troop.builder()
                    .currentHealth(200)
                    .maxHealth(200)
                    .position(new Vector2D(2.0, 3.0))
                    .movementSpeed(new MovementSpeed(1.2, 3.1, 4.4))
                    .combatRange(new CombatRange(12.0, 25.0))
                    .viewDistance(new ViewDistance(1245.0, 124.0, 123.0));
            Troop troop = troops.createTroop(tb);
            System.out.println(troop.getID());

        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
