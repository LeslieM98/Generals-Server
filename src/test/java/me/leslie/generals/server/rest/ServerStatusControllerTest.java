package me.leslie.generals.server.rest;

import static org.assertj.core.api.Assertions.assertThat;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

@ExtendWith(MockitoExtension.class)
public class ServerStatusControllerTest {
    @InjectMocks
    ServerStatusController statusController;
     
     
    @Test
    public void testPing() 
    {
 
        // when
        ResponseEntity<String> result = statusController.ping();
 
        // then
        assertThat(result.getStatusCode()).isEqualTo(HttpStatus.OK);
        assertThat(result.getBody()).isEqualTo("pong");
         
    }
}