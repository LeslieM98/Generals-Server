package me.leslie.generals.server.service;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.util.List;
import java.util.MissingResourceException;
import java.util.Optional;
import java.util.Set;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;
import static org.mockito.Mockito.*;

import me.leslie.generals.server.model.gameentity.Army;
import me.leslie.generals.server.repository.gameentity.IArmyRepository;
import me.leslie.generals.server.repository.gameentity.ITroopRepository;
import me.leslie.generals.server.valueobject.TroopID;

@RunWith(MockitoJUnitRunner.class)
public class ArmyServiceTest {
    @Mock
    ITroopRepository troopRepository;

    @Mock
    IArmyRepository armyRepository;

    @InjectMocks
    ArmyService armyService;

    private List<Army> getTestArmies() {
        return List.of(new Army(new TroopID(0), Set.of(new TroopID(3), new TroopID(4), new TroopID(5))),
                new Army(new TroopID(1), Set.of(new TroopID(6), new TroopID(7), new TroopID(8))),
                new Army(new TroopID(2), Set.of(new TroopID(9), new TroopID(10), new TroopID(11))));
    }

    @Test
    public void nullInCtor() {
        assertThrows(NullPointerException.class, () -> new ArmyService(null, troopRepository));
        assertThrows(NullPointerException.class, () -> new ArmyService(armyRepository, null));
        assertThrows(NullPointerException.class, () -> new ArmyService(null, null));
    }

    @Test
    public void deleteNull() {
        assertThrows(NullPointerException.class, () -> armyService.delete(null));
    }

    @Test
    public void saveNull() {
        assertThrows(NullPointerException.class, () -> armyService.save(null));
    }

    @Test
    public void getNull() {
        assertThrows(NullPointerException.class, () -> armyService.get(null));
    }

    @Test
    public void save() {
        var army = new Army(new TroopID(0), Set.of(new TroopID(0), new TroopID(1)));

        armyService.save(army);

        verify(armyRepository).save(army);
    }

    @Test
    public void getAll() {
        var armies = getTestArmies();

        when(armyRepository.findAll()).thenReturn(armies);

        assertEquals(armies.size(), armyService.getAll().size());
        assertTrue(armyService.getAll().containsAll(armies));
    }

    @Test
    public void get() {
        var received = getTestArmies().get(0);

        when(armyRepository.findById(received.getHq())).thenReturn(Optional.of(received));

        assertEquals(received, armyService.get(received.getHq()));
    }

    @Test(expected = MissingResourceException.class)
    public void getNonExisting() {
        when(armyRepository.findById(new TroopID(0))).thenReturn(Optional.empty());

        armyService.get(new TroopID(0));
    }

    @Test
    public void delete() {
        var toDelete = getTestArmies().get(0);
        when(armyRepository.findById(toDelete.getHq())).thenReturn(Optional.of(toDelete));

        assertEquals(toDelete, armyService.delete(toDelete.getHq()));
        verify(armyRepository).deleteById(toDelete.getHq());
    }

    @Test(expected = MissingResourceException.class)
    public void deleteNonExisting() {
        var toDelete = getTestArmies().get(0);
        when(armyRepository.findById(toDelete.getHq())).thenReturn(Optional.empty());

        armyService.delete(toDelete.getHq());
    }
}