package me.leslie.generals.game.repository;

import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.ToString;
import me.leslie.generals.game.entity.Army;
import me.leslie.generals.game.entity.HQ;
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
public class HQHashMapRepository implements HQRepository {
    private final Map<Integer, HQ> registeredHQs;
    private int nextId = 0;

    public HQHashMapRepository() {
        this.registeredHQs = new HashMap<>();
    }

    private int nextId() {
        return nextId++;
    }

    @Override
    public HQ createHQ(int currentHealth, int maxHealth, Vector2D position, MovementSpeed movementSpeed, AttackDistance attackDistance, ViewDistance viewDistance, Army army) {
        HQ hq = new HQ(nextId(), currentHealth, maxHealth, position, movementSpeed, attackDistance, viewDistance, army);
        registeredHQs.put(hq.getID(), hq);
        return hq;
    }

    public HQ updateHQ(HQ hq) {
        if (registeredHQs.get(hq.getID()) == null) {
            throw new IllegalArgumentException("Trying to update unknown HQ");
        }
        registeredHQs.put(hq.getID(), hq);
        return hq;
    }

    @Override
    public HQ deleteHQ(HQ hq) {
        HQ oldHQ = registeredHQs.get(hq.getID());
        if (oldHQ == null) {
            throw new IllegalArgumentException("Trying to update unknown HQ");
        }
        return oldHQ;
    }

    @Override
    public HQ get(int id) {
        HQ hq = registeredHQs.get(id);
        if (hq == null) {
            throw new IllegalArgumentException("Unknown id");
        }
        return hq;
    }

    @Override
    public List<HQ> getHQs() {
        return List.copyOf(registeredHQs.values());
    }


}
