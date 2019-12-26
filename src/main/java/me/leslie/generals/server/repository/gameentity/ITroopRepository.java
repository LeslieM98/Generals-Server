package me.leslie.generals.server.repository.gameentity;

import me.leslie.generals.server.model.gameentity.Troop;
import me.leslie.generals.server.valueobject.TroopID;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ITroopRepository extends CrudRepository<Troop, TroopID> {

}
