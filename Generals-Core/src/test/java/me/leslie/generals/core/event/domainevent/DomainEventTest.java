package me.leslie.generals.core.domainevent;

import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

public class DomainEventTest {
    private static class DummyEvent extends DomainEvent {
        public DummyEvent(int iterationID) {
            super(iterationID);
        }
    }

    @Test
    void testComparing() {
        List<DomainEvent> list = new ArrayList<>();
        list.add(new DummyEvent(9));
        list.add(new DummyEvent(12));
        list.add(new DummyEvent(1));

        Collections.sort(list);

        assertEquals(1, list.get(0).getIterationID());
    }

    @Test
    void incorrectConstructorThrowsException() {
        assertThrows(IllegalStateException.class, () -> new DummyEvent(-99));
    }
}
