import api.apiControllers.PlayApiController;
import api.daos.DaoFactory;
import api.daos.memory.DaoMemoryFactory;
import api.dtos.PlayDto;
import api.dtos.PlaylistDto;
import api.entities.PlayInfo;
import http.*;
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

    @Test
    void testDelete() {
        String id = this.createPlay("one play", "one author", PlayInfo.ROCK);
        HttpRequest request = HttpRequest.builder().path(PlayApiController.PLAYS).path(PlayApiController.ID_ID)
                .expandPath(id).delete();
        new Client().submit(request);
    }

    @Test
    void testFindByName(){
        this.createPlay("name one", "one author 1", PlayInfo.HIP_HOP);
        String nameid = this.createPlay("name two", "one author 2", PlayInfo.HIP_HOP);
        HttpRequest request = HttpRequest.builder().path(PlayApiController.PLAYS).path(PlayApiController.SEARCH).param("nameplay", nameid).get();
        List<PlaylistDto> playlistDtos = (List<PlaylistDto>) new Client().submit(request).getBody();

        assertEquals(1, playlistDtos.size());
    }

    @Test
    void testFindByNameNotFound(){
        String id = this.createPlay("one name", "one author", PlayInfo.ROCK);
        HttpRequest request = HttpRequest.builder().path(PlayApiController.PLAYS).path(PlayApiController.SEARCH).param("nameplay", id+2).get();
        List<PlaylistDto> playlistDtos = (List<PlaylistDto>) new Client().submit(request).getBody();

        assertTrue(playlistDtos.isEmpty());
    }

}
