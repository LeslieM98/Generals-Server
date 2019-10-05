package me.leslie.generals.game;

import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.NonNull;
import lombok.ToString;
import me.leslie.generals.GameState;
import me.leslie.generals.game.entity.Team;
import me.leslie.generals.game.repository.ArmyRepository;
import me.leslie.generals.game.repository.HQRepository;
import me.leslie.generals.game.repository.TroupRepository;

@EqualsAndHashCode
@ToString
@AllArgsConstructor
public class GameStateFactory {
    @NonNull
    private final TroupRepository troups;
    @NonNull
    private final ArmyRepository armies;
    @NonNull
    private final HQRepository hqs;

    public GameState produce(Team team) {
        return null;
    }
}
