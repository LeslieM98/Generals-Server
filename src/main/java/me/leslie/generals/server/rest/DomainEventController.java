package me.leslie.generals.server.rest;

import com.google.gson.Gson;
import me.leslie.generals.server.model.gameentity.Troop;
import me.leslie.generals.server.repository.event.domain.DomainEventRepository;
import me.leslie.generals.server.repository.gameentity.TroopRepository;
import me.leslie.generals.server.valueobject.event.domain.Attack;
import me.leslie.generals.server.valueobject.event.domain.Movement;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Optional;

@RestController
@RequestMapping("/event/domain/*")
public class DomainEventController {

    private final Logger logger = LoggerFactory.getLogger(getClass());
    private TroopRepository troopRepository;
    private DomainEventRepository domainEventRepository;

    @Autowired
    public DomainEventController(TroopRepository troopRepository, DomainEventRepository domainEventRepository) {
        this.troopRepository = troopRepository;
        this.domainEventRepository = domainEventRepository;
    }

    @GetMapping(value = "attack")
    public ResponseEntity<String> attack(@RequestBody String json) {
        Attack attack = new Gson().fromJson(json, Attack.class);

        Optional<Troop> source = troopRepository.findById(attack.getSource());
        if (source.isEmpty()) {
            String errorMessage = "Unknown damage source";
            logger.info("Attack request failed ({}) for: {}", errorMessage, attack);
            return new ResponseEntity<>(errorMessage, HttpStatus.NOT_FOUND);
        }

        Optional<Troop> target = troopRepository.findById(attack.getTarget());
        if (target.isEmpty()) {
            String errorMessage = "Unknown target";
            logger.info("Attack request failed ({}) for: {}", errorMessage, attack);
            return new ResponseEntity<>(errorMessage, HttpStatus.NOT_FOUND);
        }

        Troop targetValue = target.get();
        troopRepository.save(targetValue.recieveDamage(attack.getDamage()));
        logger.info("Attack request successful for: {}", attack);
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @GetMapping(value = "movement")
    public ResponseEntity<String> movement(@RequestBody String json) {
        Movement movement = new Gson().fromJson(json, Movement.class);

        Optional<Troop> troop = troopRepository.findById(movement.getTroop());
        if (troop.isEmpty()) {
            String errorMessage = "Unknown troop";
            logger.info("Movement request failed ({}) for: {}", errorMessage, movement);
            return new ResponseEntity<>(errorMessage, HttpStatus.NOT_FOUND);
        }

        Troop troopValue = troop.get();
        troopRepository.save(troopValue.move(movement.getNewPosition()));

        logger.info("Movement request successful for: {}", movement);
        return new ResponseEntity<>(HttpStatus.OK);
    }
}
