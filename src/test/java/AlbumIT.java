import api.apiControllers.AlbumApiController;
import api.apiControllers.PlayApiController;
import api.daos.DaoFactory;
import api.daos.memory.DaoMemoryFactory;
import api.dtos.AlbumDto;
import api.dtos.PlayDto;
import api.entities.Play;
import http.*;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

public class AlbumIT {

    @BeforeAll
    static void before(){
        DaoFactory.setFactory(new DaoMemoryFactory());
    }

    @Test
    void testCreateAlbum(){
        this.createAlbum("Thriller");
    }

    private String createAlbum(String albumName){
        HttpRequest request = HttpRequest.builder(AlbumApiController.ALBUMS).body(new AlbumDto(albumName,null)).post();
        return (String) new Client().submit(request).getBody();
    }

    private String createPlay(String playName, String author){
        HttpRequest request = HttpRequest.builder(PlayApiController.PLAYS).body(new PlayDto(playName, author)).post();
         return (String) new Client().submit(request).getBody();
    }

    @Test
    void testAlbumInvalidRequest(){
        HttpRequest request = HttpRequest.builder(AlbumApiController.ALBUMS + "/01").body(null).post();
        HttpException exception = assertThrows(HttpException.class, () -> new Client().submit(request));
        assertEquals(HttpStatus.BAD_REQUEST, exception.getHttpStatus());
    }

    @Test
    void testCreateAlbumWithoutAlbumDto() {
        HttpRequest request = HttpRequest.builder(AlbumApiController.ALBUMS).body(null).post();
        HttpException exception = assertThrows(HttpException.class, () -> new Client().submit(request));
        assertEquals(HttpStatus.BAD_REQUEST, exception.getHttpStatus());
    }

    @Test
    void testCreateAlbumWithoutAlbumDtoName() {
        HttpRequest request = HttpRequest.builder(AlbumApiController.ALBUMS).body(new AlbumDto(null,null)).post();
        HttpException exception = assertThrows(HttpException.class, () -> new Client().submit(request));
        assertEquals(HttpStatus.BAD_REQUEST, exception.getHttpStatus());
    }

    @Test
    void testUpdatePlayers() {
        String id = this.createAlbum("one album");
        List<String> players = new ArrayList<String>();

        for (int i = 0; i < 10; i++) createPlay("name"+i, "author"+i);

        HttpRequest request = HttpRequest.builder(AlbumApiController.ALBUMS).path(AlbumApiController.ID_ID)
                .expandPath(id).path(AlbumApiController.PLAYS).body(players).patch();
        new Client().submit(request);
    }



}
