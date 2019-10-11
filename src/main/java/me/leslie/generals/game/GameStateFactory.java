package me.leslie.generals.game;

import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.NonNull;
import lombok.ToString;
import me.leslie.generals.GameState;
import me.leslie.generals.game.entity.Team;
import me.leslie.generals.game.repository.ArmyRepository;
import me.leslie.generals.game.repository.TroopRepository;

@EqualsAndHashCode
@ToString
@AllArgsConstructor
public class GameStateFactory {
    @NonNull
    private final TroopRepository troups;
    @NonNull
    private final ArmyRepository armies;

    public GameState produce(Team team) {
        return null;
    }
}
