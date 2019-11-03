//package me.leslie.generals.server.eventhandler;
//
//import me.leslie.generals.core.entity.Troop;
//import me.leslie.generals.core.event.Movement;
//import me.leslie.generals.server.persistence.EventLogger;
//import me.leslie.generals.server.repository.TroopRepository;
//import org.junit.jupiter.api.Test;
//
//import static org.mockito.Mockito.*;
//
//public class MovementHandlerTest {
//
//    private final EventLogger loggerMock = mock(EventLogger.class);
//    private final TroopRepository repositoryMock = mock(TroopRepository.class);
//
//    @Test
//    void testCreation() {
//        Movement eventMock = mock(Movement.class);
//        Troop troopMock = mock(Troop.class);
//        Troop.TroopBuilder troopBuilderMock = mock(Troop.TroopBuilder.class);
//
//        when(eventMock.getTroop()).thenReturn(troopMock);
//        when(troopMock.copy()).thenReturn(troopBuilderMock);
//        when(troopBuilderMock.position(any())).thenReturn(troopBuilderMock);
//        when(troopBuilderMock.build()).thenReturn(troopMock);
//
//        MovementHandler subject = new MovementHandler(loggerMock, repositoryMock);
//        subject.handleMovement(eventMock);
//
//        verify(repositoryMock).updateTroop(troopMock);
//        verify(troopBuilderMock).position(eventMock.getNewPosition());
//        verify(loggerMock).log(eventMock);
//    }
//}
