package me.leslie.generals.server.repository.gameentity;

import lombok.Value;
import me.leslie.generals.server.model.gameentity.Army;
import me.leslie.generals.server.model.gameentity.Troop;
import me.leslie.generals.server.valueobject.TroopID;
import org.jooq.lambda.tuple.Tuple2;
import org.springframework.data.repository.NoRepositoryBean;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;
import java.util.stream.StreamSupport;

@NoRepositoryBean
@Value
public class ValidatingArmyRepository implements IArmyRepository {
    IArmyRepository armyRepository;
    ITroopRepository troopRepository;

    private List<TroopID> findNonMatchingArmyMembers(Army army, ITroopRepository troopRepository) {
        List<Troop> fetchedTrooops = new ArrayList<>();
        troopRepository.findAllById(army.getTroopIDS()).forEach(fetchedTrooops::add);
        return fetchedTrooops
                .stream()
                .map(Troop::getId)
                .filter(army.getTroopIDS()::contains)
                .collect(Collectors.toList());
    }

    @Override
    public <S extends Army> S save(S entity) {
        List<TroopID> nonMatching = findNonMatchingArmyMembers(entity, troopRepository);
        if (nonMatching.isEmpty()) {
            return armyRepository.save(entity);
        }
        throw new IllegalStateException(String.format("Saving failed because of non existing troops: %s", nonMatching.toString()));
    }

    @Override
    public <S extends Army> Iterable<S> saveAll(Iterable<S> entities) {
        List<String> errorMessages = StreamSupport.stream(entities.spliterator(), false)
                .map(army -> new Tuple2<>(army, findNonMatchingArmyMembers(army, troopRepository)))
                .filter(x -> !x.v2().isEmpty())
                .map(x -> String.format("Non existing Troops for Army %s: %s", x.v1().getHq().getValue(), Arrays.toString(x.v2().toArray())))
                .collect(Collectors.toList());
        if (errorMessages.isEmpty()) {
            return armyRepository.saveAll(entities);
        }

        throw new IllegalStateException(String.format("Saving failed for: %s", errorMessages));
    }

    @Override
    public Optional<Army> findById(TroopID troopID) {
        return armyRepository.findById(troopID);
    }

    @Override
    public boolean existsById(TroopID troopID) {
        return armyRepository.existsById(troopID);
    }

    @Override
    public Iterable<Army> findAll() {
        return armyRepository.findAll();
    }

    @Override
    public Iterable<Army> findAllById(Iterable<TroopID> troopIDS) {
        return armyRepository.findAllById(troopIDS);
    }

    @Override
    public long count() {
        return armyRepository.count();
    }

    @Override
    public void deleteById(TroopID troopID) {
        armyRepository.deleteById(troopID);
    }

    @Override
    public void delete(Army entity) {
        armyRepository.delete(entity);
    }

    @Override
    public void deleteAll(Iterable<? extends Army> entities) {
        armyRepository.deleteAll(entities);
    }

    @Override
    public void deleteAll() {
        armyRepository.deleteAll();
    }
}
