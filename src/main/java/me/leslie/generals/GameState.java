package me.leslie.generals;

import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.NonNull;
import lombok.ToString;
import me.leslie.generals.game.entity.Army;
import me.leslie.generals.game.entity.HQ;
import me.leslie.generals.game.entity.Troup;
import me.leslie.generals.game.repository.ArmyRepository;
import me.leslie.generals.game.repository.HQRepository;
import me.leslie.generals.game.repository.TroupRepository;

import java.util.Collections;
import java.util.List;

@AllArgsConstructor
@ToString
@EqualsAndHashCode
public class GameState {
    @NonNull
    private final TroupRepository troups;
    @NonNull
    private final ArmyRepository armies;
    @NonNull
    private final HQRepository hqs;

    public List<Troup> getTroups() {
        return Collections.unmodifiableList(troups.getTroups());
    }

    public List<Army> getArmies() {
        return Collections.unmodifiableList(armies.getArmies());
    }

    public List<HQ> getHQs() {
        return Collections.unmodifiableList(hqs.getHQs());
    }

}
