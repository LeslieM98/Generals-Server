package me.leslie.generals.server.repository;

import me.leslie.generals.server.model.Troop;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface TroopRepository extends CrudRepository<Troop, Integer> {

}
