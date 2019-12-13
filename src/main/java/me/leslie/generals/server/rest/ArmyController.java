package me.leslie.generals.server.rest;

import me.leslie.generals.server.model.Army;
import me.leslie.generals.server.valueobject.TroopID;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Set;

@RestController
@RequestMapping("/army/*")
public class ArmyController {
    @RequestMapping("get")
    public Army army() {
        return new Army(
                new TroopID(1),
                Set.of(new TroopID(2)));
    }
}
