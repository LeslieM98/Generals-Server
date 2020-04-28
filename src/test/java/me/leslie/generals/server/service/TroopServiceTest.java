package me.leslie.generals.server.service;

import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.junit.jupiter.api.Assertions.fail;
import static org.mockito.Mockito.*;

import java.util.HashSet;
import java.util.List;
import java.util.MissingResourceException;
import java.util.Optional;
import java.util.Set;
import java.util.stream.Collectors;

import org.junit.Test;
import org.junit.jupiter.api.AfterEach;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;

import me.leslie.generals.server.model.gameentity.Army;
import me.leslie.generals.server.model.gameentity.Troop;
import me.leslie.generals.server.repository.gameentity.IArmyRepository;
import me.leslie.generals.server.repository.gameentity.ITroopRepository;
import me.leslie.generals.server.valueobject.CombatRange;
import me.leslie.generals.server.valueobject.Health;
import me.leslie.generals.server.valueobject.MovementSpeed;
import me.leslie.generals.server.valueobject.TroopID;
import me.leslie.generals.server.valueobject.Vector2D;
import me.leslie.generals.server.valueobject.ViewDistance;

@RunWith(MockitoJUnitRunner.class)
public class TroopServiceTest {
    @Mock
    ITroopRepository troopRepository;

    @Mock
    IArmyRepository armyRepository;

    @InjectMocks
    TroopService troopService;

    private List<Troop> getTestTroops() {
        return List.of(
                new Troop(new TroopID(0), new Health(10, 10), new Vector2D(0.0, 0.0), new MovementSpeed(5.0, 10.0, 1.0),
                        new CombatRange(1.0, 5.0), new ViewDistance(5.0, 1.0, 10.0)),
                new Troop(new TroopID(1), new Health(10, 10), new Vector2D(0.0, 0.0), new MovementSpeed(5.0, 10.0, 1.0),
                        new CombatRange(1.0, 5.0), new ViewDistance(5.0, 1.0, 10.0)),
                new Troop(new TroopID(2), new Health(10, 10), new Vector2D(0.0, 0.0), new MovementSpeed(5.0, 10.0, 1.0),
                        new CombatRange(1.0, 5.0), new ViewDistance(5.0, 1.0, 10.0)),
                new Troop(new TroopID(3), new Health(10, 10), new Vector2D(0.0, 0.0), new MovementSpeed(5.0, 10.0, 1.0),
                        new CombatRange(1.0, 5.0), new ViewDistance(5.0, 1.0, 10.0)));
    }

    @Test
    public void nullInCtor() {
        assertThrows(NullPointerException.class, () -> new TroopService(null, armyRepository));
        assertThrows(NullPointerException.class, () -> new TroopService(troopRepository, null));
        assertThrows(NullPointerException.class, () -> new TroopService(null, null));
        assertDoesNotThrow(() -> new TroopService(troopRepository, armyRepository));
    }

    @Test(expected = MissingResourceException.class)
    public void deleteNonExistingTroop() {
        var searchedID = new TroopID(1);
        when(troopRepository.findById(searchedID)).thenReturn(Optional.empty());

        troopService.delete(searchedID);
        fail("Did not throw exception");
    }

    @Test
    public void deleteExistingTroop() {
        var toDelete = new TroopID(1);
        var expected = getTestTroops().get(1);
        when(troopRepository.findById(toDelete)).thenReturn(Optional.of(expected));

        var result = troopService.delete(toDelete);
        assertEquals(expected, result);
        verify(troopRepository).deleteById(toDelete);
    }

    @Test
    public void getAll() {
        when(troopRepository.findAll()).thenReturn(getTestTroops());

        assertEquals(getTestTroops().size(), troopService.getAll().size());
        assertTrue(troopService.getAll().containsAll(getTestTroops()));
    }

    @Test
    public void getAllEmpty() {
        when(troopRepository.findAll()).thenReturn(List.of());

        assertTrue(troopService.getAll().isEmpty());
    }

    @Test
    public void get() {
        var expectedID = new TroopID(1);
        var expectedTroop = getTestTroops().get(1);
        when(troopRepository.findById(expectedID)).thenReturn(Optional.of(expectedTroop));

        var result = troopService.get(expectedID);
        assertEquals(getTestTroops().get(1), result);
    }

    @Test(expected = MissingResourceException.class)
    public void getNonExisting() {
        var expectedID = new TroopID(1);
        when(troopRepository.findById(expectedID)).thenReturn(Optional.empty());

        troopService.get(expectedID);
        fail("Did not throw exception");
    }

    @Test
    public void getAllIDs() {
        var troopIDs = getTestTroops().stream().map(Troop::getId).collect(Collectors.toList());
        when(troopRepository.findAllTroopIDs()).thenReturn(troopIDs);

        assertEquals(troopIDs.size(), troopService.getAllIDs().size());
    }

    @Test
    public void getAllIDsEmpty() {
        when(troopRepository.findAllTroopIDs()).thenReturn(List.of());

        assertEquals(0, troopService.getAllIDs().size());
    }

    @Test
    public void save() {
        var troop = getTestTroops().get(0);
        troopService.save(troop);
        verify(troopRepository).save(troop);
    }

    @Test
    public void deleteTroopThatIsInArmy() {
        var troopIDs = getTestTroops().stream().map(Troop::getId).collect(Collectors.toList());
        var toRemoveTroop = troopIDs.get(1);
        var army = new Army(troopIDs.get(0), Set.copyOf(troopIDs));
        var updatedIDs = new HashSet<>(army.getTroopIDS());
        updatedIDs.remove(toRemoveTroop);
        var updatedArmy = new Army(army.getHq(), updatedIDs);

        when(armyRepository.findAll()).thenReturn(List.of(army));
        when(troopRepository.findById(toRemoveTroop))
                .thenReturn(Optional.of(getTestTroops().get(toRemoveTroop.getValue())));
        troopService.delete(toRemoveTroop);

        verify(troopRepository).deleteById(toRemoveTroop);
        verify(armyRepository).save(updatedArmy);
    }

    @Test(expected = NullPointerException.class)
    public void saveNull() {
        troopService.save(null);
    }

    @Test(expected = NullPointerException.class)
    public void deleteNull() {
        troopService.delete(null);
    }

    @Test(expected = NullPointerException.class)
    public void getNull() {
        troopService.get(null);
    }

    // TODO: Delete Troop which is HQ of Army
    // TODO: Delete Troop that is not in an Army but an Army exists on the side
    // ! which stays untouched

}