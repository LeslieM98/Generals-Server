package me.leslie.generals.server.persistence.eventlogging;

import lombok.Getter;
import lombok.Setter;
import me.leslie.generals.core.domainevent.DomainEvent;
import org.junit.jupiter.api.Test;

import java.io.Serializable;
import java.util.ArrayDeque;
import java.util.List;
import java.util.Objects;

import static org.junit.jupiter.api.Assertions.assertEquals;

class BasicEventLoggerTest {

    @Getter
    @Setter
    private static class DummyEvent extends DomainEvent implements Serializable {
        private int i;

        public DummyEvent(int i) {
            super(i);
            this.i = i;
        }

        @Override
        public boolean equals(Object o) {
            if (this == o) return true;
            if (o == null || getClass() != o.getClass()) return false;
            if (!super.equals(o)) return false;
            DummyEvent that = (DummyEvent) o;
            return i == that.i;
        }

        @Override
        public int hashCode() {
            return Objects.hash(super.hashCode(), i);
        }
    }

    @Test
    void correctLogging() {
        BasicEventLogger subject = new BasicEventLogger(new ArrayDeque<>());
        DomainEvent d0 = new DummyEvent(0);
        DomainEvent d1 = new DummyEvent(1);
        DomainEvent d2 = new DummyEvent(2);

        subject.log(d0);
        subject.log(d1);
        subject.log(d2);

        List<DomainEvent> domainEvents = subject.getEventsAsList();

        assertEquals(d0, domainEvents.get(0));
        assertEquals(d1, domainEvents.get(1));
        assertEquals(d2, domainEvents.get(2));
    }

    @Test
    void serialization() {
        BasicEventLogger source = new BasicEventLogger(new ArrayDeque<>());
        DomainEvent d0 = new DummyEvent(0);
        DomainEvent d1 = new DummyEvent(1);
        DomainEvent d2 = new DummyEvent(2);

        source.log(d0);
        source.log(d1);
        source.log(d2);

        BasicEventLogger target = new BasicEventLogger(source.serialize());
        assertEquals(source.getEventsAsList(), target.getEventsAsList());
    }
}
