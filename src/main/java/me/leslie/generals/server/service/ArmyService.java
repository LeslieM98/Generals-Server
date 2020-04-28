package me.leslie.generals.server.service;

import java.util.List;
import java.util.MissingResourceException;
import java.util.Optional;
import java.util.stream.Collectors;
import java.util.stream.StreamSupport;

import me.leslie.generals.server.model.gameentity.Army;
import me.leslie.generals.server.repository.gameentity.IArmyRepository;
import me.leslie.generals.server.repository.gameentity.ITroopRepository;
import me.leslie.generals.server.repository.gameentity.ValidatingArmyRepository;
import me.leslie.generals.server.valueobject.TroopID;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;

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

    public void save(@NonNull Army army) {
        armyRepository.save(army);
    }

    public Army delete(@NonNull TroopID hq) {
        Optional<Army> army = armyRepository.findById(hq);
        if (army.isEmpty()) {
            throw new MissingResourceException("Cannot find Army", Army.class.getName(), hq.toString());
        }
        armyRepository.deleteById(hq);
        return army.get();
    }

    public List<Army> getAll() {
        return StreamSupport.stream(armyRepository.findAll().spliterator(), false).collect(Collectors.toList());
    }

    public Army get(@NonNull TroopID hq) {
        Optional<Army> army = armyRepository.findById(hq);
        if (army.isEmpty()) {
            logger.info("Get request not successful for: {}", hq);
            throw new MissingResourceException("Cannot find Army", Army.class.getName(), hq.toString());
        }
        return army.get();
    }

}