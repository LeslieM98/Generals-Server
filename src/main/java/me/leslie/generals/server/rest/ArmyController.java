package me.leslie.generals.server.rest;

import me.leslie.generals.server.model.Troop;
import me.leslie.generals.server.valueobject.*;
import me.leslie.generals.server.valueobject.Army;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Set;

@RestController
@RequestMapping("/army/*")
public class ArmyController {
    @RequestMapping("get")
    public Army army() {
        return new Army(
                new Troop(new TroopID(1),
                        1,
                        1,
                        new Vector2D(1.0, 1.0),
                        new MovementSpeed(1.0,
                                1.0,
                                1.0),
                        new CombatRange(1.0, 1.0),
                        new ViewDistance(1.0,
                                1.0,
                                1.0)),
                Set.of(new Troop(new TroopID(2),
                        1,
                        1,
                        new Vector2D(1.0, 1.0),
                        new MovementSpeed(1.0,
                                1.0,
                                1.0),
                        new CombatRange(1.0, 1.0),
                        new ViewDistance(1.0,
                                1.0,
                                1.0))));
    }
}
