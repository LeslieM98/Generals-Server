package me.leslie.generals.server.repository.gameentity;

import me.leslie.generals.server.model.gameentity.Troop;
import me.leslie.generals.server.valueobject.TroopID;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;

@Repository
public interface ITroopRepository extends CrudRepository<Troop, TroopID> {
    default List<TroopID> findAllTroopIDs() {
        List<TroopID> ids = new ArrayList<>();
        findAll().forEach(x -> ids.add(x.getId()));
        return ids;
    }
}
