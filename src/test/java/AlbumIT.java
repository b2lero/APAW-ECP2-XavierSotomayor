import api.apiControllers.AlbumApiController;
import api.apiControllers.PlayApiController;
import api.daos.DaoFactory;
import api.daos.memory.DaoMemoryFactory;
import api.dtos.AlbumDto;
import api.dtos.PlayDto;
import api.entities.Album;
import api.entities.Play;
import api.entities.PlayInfo;
import api.exceptions.NotFoundException;
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
        HttpRequest request = HttpRequest.builder().path(AlbumApiController.ALBUMS).body(new AlbumDto(albumName,null)).post();
        return (String) new Client().submit(request).getBody();
    }

    @Test
    void testAlbumInvalidRequest(){
        HttpRequest request = HttpRequest.builder().path(AlbumApiController.ALBUMS + "/01").body(null).post();
        HttpException exception = assertThrows(HttpException.class, () -> new Client().submit(request));
        assertEquals(HttpStatus.BAD_REQUEST, exception.getHttpStatus());
    }

    @Test
    void testCreateAlbumWithoutAlbumDto() {
        HttpRequest request = HttpRequest.builder().path(AlbumApiController.ALBUMS).body(null).post();
        HttpException exception = assertThrows(HttpException.class, () -> new Client().submit(request));
        assertEquals(HttpStatus.BAD_REQUEST, exception.getHttpStatus());
    }

    @Test
    void testCreateAlbumWithoutAlbumDtoName() {
        HttpRequest request = HttpRequest.builder().path(AlbumApiController.ALBUMS).body(new AlbumDto(null,null)).post();
        HttpException exception = assertThrows(HttpException.class, () -> new Client().submit(request));
        assertEquals(HttpStatus.BAD_REQUEST, exception.getHttpStatus());
    }

    private String createPlay(String playName, String author, PlayInfo playInfo){
        HttpRequest request = HttpRequest.builder().path(PlayApiController.PLAYS).body(new PlayDto(playName, author,playInfo)).post();
        return (String) new Client().submit(request).getBody();
    }

    @Test
    void testUpdatePlayers() {
        String id = this.createAlbum("one album");
        List<String> plays = new ArrayList<>();

        for (int i = 0; i < 3; i++) {
            String play = this.createPlay("playname" + i,"author " + i,PlayInfo.ROCK);
            plays.add(play);
        }

        HttpRequest request = HttpRequest.builder().path(AlbumApiController.ALBUMS).path(AlbumApiController.ID_ID)
                .expandPath(id).path(PlayApiController.PLAYS).body(plays).patch();
        new Client().submit(request);

    }



}
