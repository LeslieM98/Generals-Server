//package me.leslie.generals.server.eventhandler;
//
//import me.leslie.generals.core.entity.Troop;
//import me.leslie.generals.core.event.TroopCreation;
//import me.leslie.generals.server.persistence.EventLogger;
//import me.leslie.generals.server.repository.TroopRepository;
//import org.junit.jupiter.api.Test;
//
//import static org.mockito.Mockito.*;
//
//public class TroopCreationHandlerTest {
//
//    private final EventLogger loggerMock = mock(EventLogger.class);
//    private final TroopRepository repositoryMock = mock(TroopRepository.class);
//
//    @Test
//    void testCreation() {
//        TroopCreation eventMock = mock(TroopCreation.class);
//        Troop troopMock = mock(Troop.class);
//        Troop.TroopBuilder troopBuilderMock = mock(Troop.TroopBuilder.class);
//
//        when(eventMock.getTroop()).thenReturn(troopMock);
//        when(troopMock.copy()).thenReturn(troopBuilderMock);
//
//        TroopCreationHandler subject = new TroopCreationHandler(loggerMock, repositoryMock);
//        subject.handleCreation(eventMock);
//
//        verify(repositoryMock).createTroop(troopBuilderMock);
//        verify(loggerMock).log(eventMock);
//    }
//}
