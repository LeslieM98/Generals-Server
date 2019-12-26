package me.leslie.generals.server.repository.gameentity;

import me.leslie.generals.server.model.gameentity.Team;
import me.leslie.generals.server.valueobject.TeamID;
import org.springframework.data.repository.CrudRepository;

public interface TeamRepository extends CrudRepository<Team, TeamID> {
}
