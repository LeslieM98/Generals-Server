package me.leslie.generals;

import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.NonNull;
import lombok.ToString;
import me.leslie.generals.game.entity.Army;
import me.leslie.generals.game.entity.Troop;
import me.leslie.generals.game.repository.ArmyRepository;
import me.leslie.generals.game.repository.TroopRepository;

import java.util.Collections;
import java.util.List;

@AllArgsConstructor
@ToString
@EqualsAndHashCode
public class GameState {
    @NonNull
    private final TroopRepository troups;
    @NonNull
    private final ArmyRepository armies;

    public List<Troop> getTroups() {
        return Collections.unmodifiableList(List.of());
    }

    public List<Army> getArmies() {
        return Collections.unmodifiableList(armies.get());
    }

}
