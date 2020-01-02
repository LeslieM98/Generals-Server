package me.leslie.generals.server.rest;

import com.google.gson.Gson;
import me.leslie.generals.server.model.gameentity.Team;
import me.leslie.generals.server.repository.gameentity.ITeamRepository;
import me.leslie.generals.server.valueobject.TeamID;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestBody;

import java.util.Optional;
import java.util.logging.Logger;

public class TeamController {

    private final TeamID teamId;
    private final ITeamRepository teamRepository;
    private final org.slf4j.Logger logger = LoggerFactory.getLogger(getClass());

    @GetMapping(value = "getall")
    public Iterable<Team> getall() {
        logger.info("Finde den Fehler");
        return teamRepository.findAll();
    }


    @GetMapping(value = "get")
    public ResponseEntity<Team> delete(@RequestBody String json) {
        TeamID teamID = new Gson().fromJson(json, Team.class);
        Optional<Team> team = teamRepository.findById(teamId);
        Logger logger;
        if (team.isEmpty()) {
            logger.info("schonwieder ein Fehler:{}", teamID);
            return new ResponseEntity<>(HttpStatus.NOT_FOUND;
        }
        logger.info("Get request sucsessful for: {}", teamID);
        return new ResponseEntity<>(team.get(), HttpStatus.OK);
    }
}




