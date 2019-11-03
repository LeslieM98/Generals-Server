//package me.leslie.generals.server.eventhandler;
//
//import me.leslie.generals.core.entity.Troop;
//import me.leslie.generals.core.event.Attack;
//import me.leslie.generals.server.persistence.EventLogger;
//import me.leslie.generals.server.repository.TroopRepository;
//import org.junit.jupiter.api.Test;
//
//import static org.mockito.Mockito.*;
//
//class AttackHandlerTest {
//
//    private final EventLogger loggerMock = mock(EventLogger.class);
//    private final TroopRepository repositoryMock = mock(TroopRepository.class);
//
//    @Test
//    void testAttack() {
//        Attack eventMock = mock(Attack.class);
//        Troop sourceMock = mock(Troop.class);
//        Troop targetMock = mock(Troop.class);
//        Troop.TroopBuilder troopBuilderMock = mock(Troop.TroopBuilder.class);
//
//        when(eventMock.getDamage()).thenReturn(10);
//        when(eventMock.getTarget()).thenReturn(targetMock);
//        when(targetMock.copy()).thenReturn(troopBuilderMock);
//        when(targetMock.getCurrentHealth()).thenReturn(100);
//        when(troopBuilderMock.build()).thenReturn(targetMock);
//        when(troopBuilderMock.currentHealth(anyInt())).thenReturn(troopBuilderMock);
//
//        AttackHandler subject = new AttackHandler(loggerMock, repositoryMock);
//        subject.handleAttack(eventMock);
//
//        verify(repositoryMock).updateTroop(targetMock);
//        verify(troopBuilderMock).currentHealth(anyInt());
//        verify(loggerMock).log(eventMock);
//        verify(troopBuilderMock).currentHealth(90);
//    }
//}
