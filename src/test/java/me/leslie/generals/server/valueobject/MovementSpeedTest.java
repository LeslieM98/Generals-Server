package me.leslie.generals.server.valueobject;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class MovementSpeedTest {
    @Test
    void correctValues() {
        assertDoesNotThrow(() -> new MovementSpeed(1.0, 1.0, 2.0));
        assertDoesNotThrow(() -> new MovementSpeed(10000.0, 114244.0, 99999999.0));
        assertDoesNotThrow(() -> new MovementSpeed(1.0, 1535314.0, 215344514.0));
    }

    @Test
    void negativeNormalSpeed() {
        assertThrows(IllegalStateException.class, () -> new MovementSpeed(-1.0, 1.0, 1.0));
        assertThrows(IllegalStateException.class, () -> new MovementSpeed(-100000.0, 1.0, 1.0));
        try{
            new MovementSpeed(-1.0, 1.0, 1.0);
        }catch (Exception e){
            String message = e.getMessage().toLowerCase();
            assertTrue(message.contains("normal"));
            assertTrue(message.contains("speed"));
            assertTrue(message.contains("positive"));
        }
    }

    @Test
    void zeroNormalSpeed() {
        assertThrows(IllegalStateException.class, () -> new MovementSpeed(0.0, 1.0, 1.0));
        try{
            new MovementSpeed(0.0, 1.0, 1.0);
        }catch (Exception e){
            String message = e.getMessage().toLowerCase();
            assertTrue(message.contains("normal"));
            assertTrue(message.contains("speed"));
            assertTrue(message.contains("positive"));
        }
    }

    @Test
    void negativeStreetSpeed() {
        assertThrows(IllegalStateException.class, () -> new MovementSpeed(1.0, -1.0, 1.0));
        assertThrows(IllegalStateException.class, () -> new MovementSpeed(1.0, -100000.0, 1.0));
        try{
            new MovementSpeed(1.0, -1.0, 1.0);
        }catch (Exception e){
            String message = e.getMessage().toLowerCase();
            assertTrue(message.contains("street"));
            assertTrue(message.contains("speed"));
            assertTrue(message.contains("positive"));
        }
    }

    @Test
    void zeroStreetSpeed() {
        assertThrows(IllegalStateException.class, () -> new MovementSpeed(1.0, 0.0, 1.0));
        try{
            new MovementSpeed(1.0, 0.0, 1.0);
        }catch (Exception e){
            String message = e.getMessage().toLowerCase();
            assertTrue(message.contains("street"));
            assertTrue(message.contains("speed"));
            assertTrue(message.contains("positive"));
        }
    }

    @Test
    void negativeDifficultTerrainSpeed() {
        assertThrows(IllegalStateException.class, () -> new MovementSpeed(1.0, 1.0, -11.0));
        assertThrows(IllegalStateException.class, () -> new MovementSpeed(1.0, 1.0, -100000.0));
        try{
            new MovementSpeed(1.0, 1.0, -11.0);
        }catch (Exception e){
            String message = e.getMessage().toLowerCase();
            assertTrue(message.contains("difficult"));
            assertTrue(message.contains("terrain"));
            assertTrue(message.contains("speed"));
            assertTrue(message.contains("positive"));
        }
    }

    @Test
    void zeroDifficultTerainSpeed() {
        assertThrows(IllegalStateException.class, () -> new MovementSpeed(1.0, 1.0, 0.0));
        try{
            new MovementSpeed(1.0, 1.0, 0.0);
        }catch (Exception e){
            String message = e.getMessage().toLowerCase();
            assertTrue(message.contains("difficult"));
            assertTrue(message.contains("terrain"));
            assertTrue(message.contains("speed"));
            assertTrue(message.contains("positive"));
        }
    }
}
