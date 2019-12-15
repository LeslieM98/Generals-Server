package me.leslie.generals.server.rest;

import com.google.gson.Gson;
import me.leslie.generals.server.model.Troop;
import me.leslie.generals.server.repository.TroopRepository;
import me.leslie.generals.server.valueobject.TroopID;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@RestController
@RequestMapping("/troop/*")
public class TroopController {
    private final TroopRepository troopRepository;
    private final Logger logger = LoggerFactory.getLogger(getClass());

    @Autowired
    public TroopController(TroopRepository troopRepository) {
        this.troopRepository = troopRepository;
    }

    @PutMapping(value = "save")
    public ResponseEntity<String> save(@RequestBody String json) {
        Troop troop = new Gson().fromJson(json, Troop.class);
        troopRepository.save(troop);
        logger.info("Save request for: {}", troop);
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @DeleteMapping(value = "delete")
    public ResponseEntity<Troop> delete(@RequestBody String json) {
        TroopID troopID = new Gson().fromJson(json, TroopID.class);
        Optional<Troop> troop = troopRepository.findById(troopID);
        if (troop.isEmpty()) {
            logger.info("Delete request not successful for: {}", troopID);
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        troopRepository.deleteById(troopID);
        logger.info("Delete request successful for: {}", troopID);
        return new ResponseEntity<>(troop.get(), HttpStatus.OK);
    }

    @GetMapping(value = "getall")
    public Iterable<Troop> getAll() {
        logger.info("Getall request successful");
        return troopRepository.findAll();
    }

    @GetMapping(value = "get")
    public ResponseEntity<Troop> get(@RequestBody String json) {
        TroopID troopID = new Gson().fromJson(json, TroopID.class);
        Optional<Troop> troop = troopRepository.findById(troopID);
        if (troop.isEmpty()) {
            logger.info("Get request not successful for: {}", troopID);
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        logger.info("Get request successful for: {}", troopID);
        return new ResponseEntity<>(troop.get(), HttpStatus.OK);
    }
}
