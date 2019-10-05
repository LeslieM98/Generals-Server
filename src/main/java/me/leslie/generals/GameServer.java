package me.leslie.generals;

import com.github.simplenet.Server;
import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.NonNull;
import lombok.ToString;
import me.leslie.generals.game.eventhandler.AttackHandler;
import me.leslie.generals.game.eventhandler.CreationHandler;
import me.leslie.generals.game.eventhandler.MovementHandler;

@ToString
@EqualsAndHashCode
@AllArgsConstructor
public class GameServer {
    private final Server server;


    @NonNull
    private final AttackHandler attackHandler;
    @NonNull
    private final CreationHandler creationHandler;
    @NonNull
    private final MovementHandler movementHandler;


}
