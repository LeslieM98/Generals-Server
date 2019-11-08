package me.leslie.generals.core.entity.interfaces;


import java.util.List;

public interface IArmyComposition {
    ITroop getHQ();

    List<? extends ITroop> getTroops();
}
