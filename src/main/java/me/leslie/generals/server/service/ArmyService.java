package me.leslie.generals.server.service;

import java.util.List;

import com.google.gson.Gson;
import me.leslie.generals.server.model.gameentity.Army;
import me.leslie.generals.server.repository.gameentity.IArmyRepository;
import me.leslie.generals.server.repository.gameentity.ITroopRepository;
import me.leslie.generals.server.repository.gameentity.ValidatingArmyRepository;
import me.leslie.generals.server.valueobject.TroopID;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import lombok.NonNull;

public class ArmyService {
    private final IArmyRepository armyRepository;
    private final ITroopRepository troopRepository;
    private final Logger logger = LoggerFactory.getLogger(getClass());

    @Autowired
    public ArmyService(@NonNull IArmyRepository armyRepository, @NonNull ITroopRepository troopRepository) {
        this.troopRepository = troopRepository;
        this.armyRepository = new ValidatingArmyRepository(armyRepository, this.troopRepository);
    }

    public void save(Army army) {
        throw new UnsupportedOperationException("Not implemented yet");
    }

    public Army delete(Army army) {
        throw new UnsupportedOperationException("Not implemented yet");
    }

    public List<Army> getAll() {
        throw new UnsupportedOperationException("Not implemented yet");
    }

    public Army get(TroopID hq) {
        throw new UnsupportedOperationException("Not implemented yet");
    }

}