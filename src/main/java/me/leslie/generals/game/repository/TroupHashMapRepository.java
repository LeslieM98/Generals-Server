package me.leslie.generals.game.repository;

import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.ToString;
import me.leslie.generals.game.entity.Troup;
import me.leslie.generals.utility.AttackDistance;
import me.leslie.generals.utility.MovementSpeed;
import me.leslie.generals.utility.Vector2D;
import me.leslie.generals.utility.ViewDistance;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Getter
@AllArgsConstructor
@EqualsAndHashCode
@ToString
public class TroupHashMapRepository implements TroupRepository {
    private final Map<Integer, Troup> registeredTroups;
    private int nextId = 0;

    public TroupHashMapRepository() {
        this.registeredTroups = new HashMap<>();
    }

    private int nextId() {
        return nextId++;
    }

    @Override
    public Troup createTroup(int currentHealth, int maxHealth, Vector2D position, MovementSpeed movementSpeed, AttackDistance attackDistance, ViewDistance viewDistance) {
        Troup troup = new Troup(nextId(), currentHealth, maxHealth, position, movementSpeed, attackDistance, viewDistance);
        registeredTroups.put(troup.getID(), troup);
        return troup;
    }

    @Override
    public Troup updateTroup(Troup troup) {
        if (registeredTroups.get(troup.getID()) == null) {
            throw new IllegalArgumentException("Trying to update unknown Troup");
        }
        registeredTroups.put(troup.getID(), troup);
        return troup;
    }

    @Override
    public Troup deleteTroup(Troup troup) {
        Troup oldTroup = registeredTroups.get(troup.getID());
        if (oldTroup == null) {
            throw new IllegalArgumentException("Trying to update unknown Troup");
        }
        return oldTroup;
    }

    @Override
    public Troup getTroup(int id) {
        Troup troup = registeredTroups.get(id);
        if (troup == null) {
            throw new IllegalArgumentException("Unknown id");
        }
        return troup;
    }

    @Override
    public List<Troup> getTroups() {
        return List.copyOf(registeredTroups.values());
    }


}
