package me.leslie.generals.server.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import lombok.NonNull;
import me.leslie.generals.server.model.gameentity.Army;
import me.leslie.generals.server.model.gameentity.Troop;
import me.leslie.generals.server.repository.gameentity.IArmyRepository;
import me.leslie.generals.server.repository.gameentity.ITroopRepository;
import me.leslie.generals.server.valueobject.TroopID;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.MissingResourceException;
import java.util.Optional;
import java.util.Set;
import java.util.stream.Collectors;
import java.util.stream.StreamSupport;

@Service
public class TroopService {
    private final ITroopRepository troopRepository;
    private final IArmyRepository armyRepository;

    @Autowired
    public TroopService(@NonNull ITroopRepository troopRepository, @NonNull IArmyRepository armyRepository) {
        this.armyRepository = armyRepository;
        this.troopRepository = troopRepository;
    }

    public void save(@NonNull Troop troop) {
        troopRepository.save(troop);
    }

    public Troop delete(@NonNull TroopID troopID) {
        Optional<Troop> troop = troopRepository.findById(troopID);
        if (troop.isEmpty()) {
            throw new MissingResourceException("Cannot find troop", troopID.getClass().getName(), troopID.toString());
        }
        troopRepository.deleteById(troopID);
        deleteTroopFromArmy(troopID, armyRepository);
        return troop.get();
    }

    public List<Troop> getAll() {
        return StreamSupport.stream(troopRepository.findAll().spliterator(), false).collect(Collectors.toList());
    }

    public Troop get(@NonNull TroopID troopID) {
        Optional<Troop> troop = troopRepository.findById(troopID);
        if (troop.isEmpty()) {
            throw new MissingResourceException("Cannot find troop", troopID.getClass().getName(), troopID.toString());
        }
        return troop.get();
    }

    public List<TroopID> getAllIDs() {
        return troopRepository.findAllTroopIDs().stream().collect(Collectors.toList());
    }

    private void deleteTroopFromArmy(TroopID troopID, IArmyRepository armyRepository) {
        List<Army> armies = new ArrayList<>();
        armyRepository.findAll().forEach(armies::add);

        for (var army : armies) {
            if (army.getTroopIDS().contains(troopID)) {
                Set<TroopID> updatedTroops = new HashSet<>(army.getTroopIDS());
                updatedTroops.remove(troopID);
                Army updatedArmy = new Army(army.getHq(), updatedTroops);
                armyRepository.save(updatedArmy);
            }
        }
    }
}