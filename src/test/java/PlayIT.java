import api.apiControllers.PlayApiController;
import api.daos.DaoFactory;
import api.daos.memory.DaoMemoryFactory;
import api.dtos.PlayDto;
import api.dtos.PlaylistDto;
import api.entities.PlayInfo;
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
        this.createPlay("song name", "author song", PlayInfo.HIP_HOP);
    }

    private String createPlay(String playName, String author, PlayInfo playinfo){
        HttpRequest request = HttpRequest.builder().path(PlayApiController.PLAYS).body(new PlayDto(playName, author, playinfo)).post();
        return (String) new Client().submit(request).getBody();
    }

    @Test
    void testCreatePlayWithoutAuthor(){
        HttpRequest request = HttpRequest.builder().path(PlayApiController.PLAYS).body(new PlayDto("one song", null, null)).post();
        HttpException exception = assertThrows(HttpException.class, () -> new Client().submit(request));
        assertEquals(HttpStatus.BAD_REQUEST, exception.getHttpStatus());
    }

    @Test
    void testCreatePlayWithoutPlayInfo(){
        HttpRequest request = HttpRequest.builder().path(PlayApiController.PLAYS).body(new PlayDto("one song", "one author", null)).post();
        HttpException exception = assertThrows(HttpException.class, () -> new Client().submit(request));
        assertEquals(HttpStatus.BAD_REQUEST, exception.getHttpStatus());
    }

    @Test
    void testCreatePlayWithNoData(){
        HttpRequest request = HttpRequest.builder().path(PlayApiController.PLAYS).body(null).post();
        HttpException exception = assertThrows(HttpException.class, () -> new Client().submit(request));
        assertEquals(HttpStatus.BAD_REQUEST, exception.getHttpStatus());
    }

    @Test
    void testReadAll(){
        for (int i = 0; i < 5;i++){
            this.createPlay("playName-"+i, "author-"+1, PlayInfo.ROCK);
        }
        HttpRequest request = HttpRequest.builder().path(PlayApiController.PLAYS).get();
        List<PlaylistDto> playlist = (List<PlaylistDto>) new Client().submit(request).getBody();
        assertTrue(playlist.size()>=5);
    }
//
//    @Test
//    void testDelete() {
//        String nameid = this.createPlay("uno");
//        HttpRequest request1 = HttpRequest.builder(PlayApiController.PLAYS).get();
//        new Client().submit(request1);
//
//        HttpRequest request2 = HttpRequest.builder(PlayApiController.PLAYS).path(PlayApiController.ID_ID)
//                .expandPath(nameid).delete();
//        new Client().submit(request2);
//        HttpException exception = assertThrows(HttpException.class, () -> new Client().submit(request1));
//        assertEquals(HttpStatus.NOT_FOUND, exception.getHttpStatus());
//    }



}
