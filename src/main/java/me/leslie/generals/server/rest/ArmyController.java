package me.leslie.generals.server.rest;

import com.google.gson.Gson;
import me.leslie.generals.server.model.gameentity.Army;
import me.leslie.generals.server.repository.gameentity.IArmyRepository;
import me.leslie.generals.server.repository.gameentity.ITroopRepository;
import me.leslie.generals.server.repository.gameentity.ValidatingArmyRepository;
import me.leslie.generals.server.service.ArmyService;
import me.leslie.generals.server.valueobject.TroopID;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.MissingResourceException;

@RestController
@RequestMapping("/army/*")
public class ArmyController {
    private final ArmyService armyService;
    private final Logger logger = LoggerFactory.getLogger(getClass());

    @Autowired
    public ArmyController(ArmyService armyService) {
        this.armyService = armyService;
    }

    @PutMapping(value = "save")
    public ResponseEntity<String> save(String json) {
        Army army = new Gson().fromJson(json, Army.class);
        logger.info("Save request for: {}", army);
        armyService.save(army);
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @DeleteMapping(value = "delete")
    public ResponseEntity<Army> delete(@RequestBody String json) {
        try {
            TroopID hq = new Gson().fromJson(json, TroopID.class);
            var army = armyService.delete(hq);
            logger.info("Delete request successful for: {}", hq);
            return new ResponseEntity<>(army, HttpStatus.OK);
        } catch (MissingResourceException e) {
            logger.error("Delete request failed", e);
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @GetMapping(value = "getall")
    public List<Army> getAll() {
        logger.info("Getall request successful");
        return armyService.getAll();
    }

    @GetMapping(value = "get")
    public ResponseEntity<Army> get(@RequestBody String json) {
        TroopID troopID = new Gson().fromJson(json, TroopID.class);
        try {
            TroopID hq = new Gson().fromJson(json, TroopID.class);
            var army = armyService.get(hq);
            logger.info("Get request successful for: {}", troopID);
            return new ResponseEntity<>(army, HttpStatus.OK);
        } catch (MissingResourceException e) {
            logger.error("Get request failed", e);
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

}
