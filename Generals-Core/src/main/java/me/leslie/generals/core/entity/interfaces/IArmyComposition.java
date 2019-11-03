package me.leslie.generals.core.entity.interfaces;


import java.util.Set;

public interface IArmyComposition {
    ITroop getHQ();

    Set<? extends ITroop> getTroops();
}
