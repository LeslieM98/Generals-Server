package me.leslie.generals.server.rest;

import com.google.gson.Gson;
import me.leslie.generals.server.model.gameentity.Team;
import me.leslie.generals.server.repository.gameentity.ITeamRepository;
import me.leslie.generals.server.valueobject.TeamID;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestBody;

import java.util.Optional;


public class TeamController {


    private final Logger logger = LoggerFactory.getLogger(getClass());
    private final ITeamRepository teamRepository;

    //TODO: Cleanup
    @Autowired
    public TeamController(ITeamRepository teamRepository) {
        this.teamRepository = teamRepository;


    }

    @GetMapping(value = "getall")
    public Iterable<Team> getall() {
        logger.info("Getall request successful");
        return teamRepository.findAll();
    }


    @GetMapping(value = "get")
    public ResponseEntity<Team> delete(@RequestBody String json) {
        TeamID teamID = new Gson().fromJson(json, TeamID.class);
        Optional<Team> team = teamRepository.findById(teamID);
        if (team.isEmpty()) {
            logger.info("Get request not successful for:{}", teamID);
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        logger.info("Get request sucsessful for: {}", teamID);
        return new ResponseEntity<>(team.get(), HttpStatus.OK);
    }
}




