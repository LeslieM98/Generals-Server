package me.leslie.generals.game.repository;

import me.leslie.generals.game.entity.Army;
import me.leslie.generals.game.entity.MilitaryUnit;

import java.util.List;

public interface ArmyRepository {
    Army createArmy(List<MilitaryUnit> units);

    Army updateArmy(Army army);

    Army deleteArmy(Army army);

    Army get(int id);

    List<Army> getArmies();
}
