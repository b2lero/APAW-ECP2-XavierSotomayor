import api.apiControllers.PlayApiController;
import api.daos.DaoFactory;
import api.daos.memory.DaoMemoryFactory;
import api.dtos.PlayDto;
import api.dtos.PlaylistDto;
import http.Client;
import http.HttpException;
import http.HttpRequest;
import http.HttpStatus;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class PlayIT {

    @BeforeAll
    static void before(){
        DaoFactory.setFactory(new DaoMemoryFactory());
    }

    @Test
    void testCreatePlay(){
        this.createPlay("Billie Jean");
    }

    private void createPlay(String playName){
        HttpRequest request = HttpRequest.builder(PlayApiController.PLAYS).body(new PlayDto(playName, "MJ")).post();
        new Client().submit(request);
    }

    @Test
    void testCreatePlayWithoutAuthor(){
        HttpRequest request = HttpRequest.builder(PlayApiController.PLAYS).body(new PlayDto("Billie Jean", null)).post();
        HttpException exception = assertThrows(HttpException.class, () -> new Client().submit(request));
        assertEquals(HttpStatus.BAD_REQUEST, exception.getHttpStatus());
    }

    @Test
    void testCreatePlayWithNoData(){
        HttpRequest request = HttpRequest.builder(PlayApiController.PLAYS).body(null).post();
        HttpException exception = assertThrows(HttpException.class, () -> new Client().submit(request));
        assertEquals(HttpStatus.BAD_REQUEST, exception.getHttpStatus());
    }

    @Test
    void testReadAll(){
        for (int i = 0; i < 5;i++){
            this.createPlay("playName"+i);
        }
        HttpRequest request = HttpRequest.builder(PlayApiController.PLAYS).get();
        List<PlaylistDto> playlist = (List<PlaylistDto>) new Client().submit(request).getBody();
        assertTrue(playlist.size()>=5);
    }


}
