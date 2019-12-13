package me.leslie.generals.server.rest;

import me.leslie.generals.server.model.Troop;
import me.leslie.generals.server.valueobject.*;
import me.leslie.generals.server.repository.TroopRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/troop/*")
public class TroopController {
    @Autowired
    private TroopRepository troopRepository;

    @RequestMapping("create")
    public Troop create() {
        Troop t = new Troop(new TroopID(1), 1, 1, new Vector2D(1.0, 1.0), new MovementSpeed(1.0, 1.0, 1.0), new CombatRange(1.0, 1.0), new ViewDistance(1.0, 1.0, 1.0));
        troopRepository.save(t);
        return t;
    }

    @RequestMapping("delete")
    public Troop delete(){
        Troop t = new Troop(new TroopID(1), 1, 1, new Vector2D(1.0, 1.0), new MovementSpeed(1.0, 1.0, 1.0), new CombatRange(1.0, 1.0), new ViewDistance(1.0, 1.0, 1.0));
        troopRepository.delete(t);
        return t;
    }

    @RequestMapping("get")
    public Iterable<Troop> get(){
        return troopRepository.findAll();
    }
}
