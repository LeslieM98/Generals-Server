package me.leslie.generals.game.repository;

import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.ToString;
import me.leslie.generals.game.entity.Army;
import me.leslie.generals.game.entity.MilitaryUnit;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Getter
@AllArgsConstructor
@EqualsAndHashCode
@ToString
public class ArmyHashMapRepository implements ArmyRepository {
    private final Map<Integer, Army> registeredArmies;
    private int nextId = 0;


    public ArmyHashMapRepository() {
        this.registeredArmies = new HashMap<>();
    }

    private int nextId() {
        return nextId++;
    }

    @Override
    public Army createArmy(List<MilitaryUnit> units) {
        Army army = new Army(units, nextId());
        registeredArmies.put(army.getID(), army);
        return army;
    }

    @Override
    public Army updateArmy(Army army) {
        if (registeredArmies.get(army.getID()) == null) {
            throw new IllegalArgumentException("Trying to update unknown Army");
        }
        registeredArmies.put(army.getID(), army);
        return army;
    }

    @Override
    public Army deleteArmy(Army army) {
        Army oldArmy = registeredArmies.get(army.getID());
        if (oldArmy == null) {
            throw new IllegalArgumentException("Trying to update unknown Troup");
        }
        return oldArmy;
    }

    @Override
    public Army get(int id) {
        Army army = registeredArmies.get(id);
        if (army == null) {
            throw new IllegalArgumentException("Unknown ID");
        }
        return army;
    }

    @Override
    public List<Army> getArmies() {
        return List.copyOf(registeredArmies.values());
    }


}
