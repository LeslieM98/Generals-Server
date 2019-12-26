package me.leslie.generals.server.rest;

import com.google.gson.Gson;
import me.leslie.generals.server.model.gameentity.Army;
import me.leslie.generals.server.model.gameentity.Troop;
import me.leslie.generals.server.repository.gameentity.IArmyRepository;
import me.leslie.generals.server.repository.gameentity.ITroopRepository;
import me.leslie.generals.server.valueobject.TroopID;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/army/*")
public class ArmyController {

    private final IArmyRepository armyRepository;
    private final ITroopRepository troopRepository;
    private final Logger logger = LoggerFactory.getLogger(getClass());

    @Autowired
    public ArmyController(IArmyRepository armyRepository, ITroopRepository troopRepository) {
        this.armyRepository = armyRepository;
        this.troopRepository = troopRepository;
    }

    private List<TroopID> findNonMatching(Army army, ITroopRepository troopRepository) {
        List<Troop> fetchedTrooops = new ArrayList<>();
        troopRepository.findAllById(army.getTroopIDS()).forEach(fetchedTrooops::add);
        return fetchedTrooops
                .stream()
                .map(Troop::getId)
                .filter(army.getTroopIDS()::contains)
                .collect(Collectors.toList());
    }

    @PutMapping(value = "save")
    public ResponseEntity<List<TroopID>> save(String json) {
        Army army = new Gson().fromJson(json, Army.class);
        List<TroopID> nonExistingTroops = findNonMatching(army, troopRepository);
        if (nonExistingTroops.isEmpty()) {
            logger.info("Save request successful for: {}", army);
            return new ResponseEntity<>(HttpStatus.OK);
        }
        logger.info("Save request not successful for: {}", army);
        return new ResponseEntity<>(nonExistingTroops, HttpStatus.INTERNAL_SERVER_ERROR);
    }

    @DeleteMapping(value = "delete")
    public ResponseEntity<Army> delete(@RequestBody String json) {
        TroopID troopID = new Gson().fromJson(json, TroopID.class);
        Optional<Army> army = armyRepository.findById(troopID);
        if (army.isEmpty()) {
            logger.info("Delete request not successful for: {}", troopID);
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        troopRepository.deleteById(troopID);
        logger.info("Delete request successful for: {}", troopID);
        return new ResponseEntity<>(army.get(), HttpStatus.OK);
    }

    @GetMapping(value = "getall")
    public Iterable<Army> getAll() {
        logger.info("Getall request successful");
        return armyRepository.findAll();
    }

    @GetMapping(value = "get")
    public ResponseEntity<Army> get(@RequestBody String json) {
        TroopID troopID = new Gson().fromJson(json, TroopID.class);
        Optional<Army> army = armyRepository.findById(troopID);
        if (army.isEmpty()) {
            logger.info("Get request not successful for: {}", troopID);
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        logger.info("Get request successful for: {}", troopID);
        return new ResponseEntity<>(army.get(), HttpStatus.OK);
    }

}
