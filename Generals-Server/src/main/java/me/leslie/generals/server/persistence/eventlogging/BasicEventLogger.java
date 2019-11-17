package me.leslie.generals.server.persistence.eventlogging;

import lombok.AllArgsConstructor;
import me.leslie.generals.core.event.domainevent.DomainEvent;

import java.io.*;
import java.util.*;

@AllArgsConstructor
public class BasicEventLogger implements Serializable, IEventLogger {

    private final Deque<DomainEvent> events;

    public BasicEventLogger(byte[] serializedData) {
        events = new ArrayDeque<>();
        try {
            ByteArrayInputStream byteArrayInputStream = new ByteArrayInputStream(serializedData);
            ObjectInputStream objectInputStream = new ObjectInputStream(byteArrayInputStream);
            Object readObject;
            while ((readObject = objectInputStream.readObject()) != null) {
                if (readObject instanceof DomainEvent) {
                    log((DomainEvent) readObject);
                } else {
                    throw new IllegalStateException("One of the Read Objects was not subType of DomainEvent: " + readObject.toString());
                }
            }
        } catch (IOException e) {
            throw new UnsupportedOperationException("Not Implemented yet", e);
            // TODO: implement abvoe
        } catch (ClassNotFoundException e) {
            throw new UnsupportedOperationException("Not Implemented yet", e);
            // TODO: implement abvoe
        }
    }

    @Override
    public void log(DomainEvent event) {
        events.push(event);
    }

    public List<DomainEvent> getEventsAsList() {
        List<DomainEvent> copiedList = new ArrayList<>(events);
        Collections.reverse(copiedList);
        return List.copyOf(copiedList);
    }

    public byte[] serialize() {
        DomainEvent[] domainEvents = getEventsAsList().toArray(new DomainEvent[0]);
        try {
            ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
            ObjectOutputStream objectOutputStream = new ObjectOutputStream(byteArrayOutputStream);
            for (var x : domainEvents) {
                objectOutputStream.writeObject(x);
            }
            objectOutputStream.writeObject(null);
            return byteArrayOutputStream.toByteArray();
        } catch (IOException e) {
            throw new UnsupportedOperationException("Implement missing IO Exception", e);
            // TODO: implement above
        }
    }
}
