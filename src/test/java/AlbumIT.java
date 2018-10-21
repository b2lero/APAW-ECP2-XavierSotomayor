import api.apiControllers.AlbumApiController;
import api.daos.DaoFactory;
import api.daos.memory.DaoMemoryFactory;
import api.dtos.AlbumDto;
import http.Client;
import http.HttpException;
import http.HttpRequest;
import http.HttpStatus;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

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
        HttpRequest request = HttpRequest.builder(AlbumApiController.ALBUMS).body(new AlbumDto(albumName)).post();
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
    void testCreatePublisherWithoutPublisherDtoName() {
        HttpRequest request = HttpRequest.builder(AlbumApiController.ALBUMS).body(new AlbumDto(null)).post();
        HttpException exception = assertThrows(HttpException.class, () -> new Client().submit(request));
        assertEquals(HttpStatus.BAD_REQUEST, exception.getHttpStatus());
    }

}
