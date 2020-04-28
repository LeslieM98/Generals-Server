package me.leslie.generals.server.rest;

import com.google.gson.Gson;
import me.leslie.generals.server.model.gameentity.Troop;
import me.leslie.generals.server.service.TroopService;
import me.leslie.generals.server.valueobject.TroopID;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import lombok.NonNull;

import java.util.*;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/troop/*")
public class TroopController {
    private final TroopService troopService;
    private final Logger logger = LoggerFactory.getLogger(getClass());

    @Autowired
    public TroopController(@NonNull TroopService troopService) {
        this.troopService = troopService;
    }

    @PutMapping(value = "save")
    public ResponseEntity<String> save(@RequestBody String json) {
        Troop troop = new Gson().fromJson(json, Troop.class);
        troopService.save(troop);
        logger.info("Save request for: {}", troop);
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @DeleteMapping(value = "delete")
    public ResponseEntity<Troop> delete(@RequestBody String json) {
        try {
            TroopID troopID = new Gson().fromJson(json, TroopID.class);
            Troop troop = troopService.delete(troopID);
            logger.info("Delete request successful for: {}", troopID);
            return new ResponseEntity<>(troop, HttpStatus.OK);
        } catch (MissingResourceException e) {
            logger.error("Delete request failed.", e);
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @GetMapping(value = "getall")
    public List<Troop> getAll() {
        logger.info("Getall request successful");
        return troopService.getAll();
    }

    @GetMapping(value = "get")
    public ResponseEntity<Troop> get(@RequestParam int id) {
        try {
            TroopID troopID = new TroopID(id);
            Troop troop = troopService.get(troopID);
            logger.info("Get request successful for: {}", troopID);
            return new ResponseEntity<>(troop, HttpStatus.OK);
        } catch (MissingResourceException e) {
            logger.error("Get request not successful", e);
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @GetMapping(value = "getallids")
    public ResponseEntity<List<Integer>> getAllIDs() {
        ResponseEntity<List<Integer>> response = new ResponseEntity<>(
                troopService.getAllIDs().stream().map(TroopID::getValue).collect(Collectors.toList()), HttpStatus.OK);
        logger.info("GetAllIDs request successful");
        return response;
    }
}
