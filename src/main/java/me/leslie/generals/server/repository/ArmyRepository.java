package me.leslie.generals.server.repository;

import me.leslie.generals.server.model.Troop;
import me.leslie.generals.server.valueobject.Army;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ArmyRepository extends CrudRepository<Army, Troop> {

}
