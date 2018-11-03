import api.apiControllers.PublisherApiController;
import api.daos.DaoFactory;
import api.daos.memory.DaoMemoryFactory;
import api.dtos.PublisherDto;
import api.entities.Publisher;
import http.Client;
import http.HttpException;
import http.HttpRequest;
import http.HttpStatus;
import org.apache.logging.log4j.LogManager;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

public class PublisherIT {

    @BeforeAll
    static void before() {
        DaoFactory.setFactory(new DaoMemoryFactory());
    }

    @Test
    void testCreatePublisher() {
        this.createPublisher("publisher 1","label 1");
    }

    private String createPublisher(String name, String label) {
        HttpRequest request = HttpRequest.builder().path(PublisherApiController.PUBLISHERS)
                .body(new PublisherDto(name, label)).post();
         return  (String) new Client().submit(request).getBody();
    }

    @Test
    void testPublisherInvalidRequest() {
        HttpRequest request = HttpRequest.builder().path(PublisherApiController.PUBLISHERS + "/invalid").body(null).post();
        HttpException exception = assertThrows(HttpException.class, () -> new Client().submit(request));
        assertEquals(HttpStatus.BAD_REQUEST, exception.getHttpStatus());
    }

    @Test
    void testCreatePublisherWithoutPublisherDto() {
        HttpRequest request = HttpRequest.builder().path(PublisherApiController.PUBLISHERS).body(null).post();
        HttpException exception = assertThrows(HttpException.class, () -> new Client().submit(request));
        assertEquals(HttpStatus.BAD_REQUEST, exception.getHttpStatus());
    }

    @Test
    void testCreatePublisherWithoutPublisherDtoName() {
        HttpRequest request = HttpRequest.builder().path(PublisherApiController.PUBLISHERS).body(new PublisherDto(null, "un label")).post();
        HttpException exception = assertThrows(HttpException.class, () -> new Client().submit(request));
        assertEquals(HttpStatus.BAD_REQUEST, exception.getHttpStatus());
    }

    @Test
    void testUpdatePublisher(){
        String id = this.createPublisher("un publisher viejo", "un label");
        LogManager.getLogger().info("publisherid: "+ id);
        HttpRequest request = HttpRequest.builder().path(PublisherApiController.PUBLISHERS).path(PublisherApiController.ID_ID)
                .expandPath(id).body(new PublisherDto("un publisher nuevo", null)).put();
        new Client().submit(request);
    }

    @Test
    void testUpdatePublisherWithoutPublisherDto() {
        String id = this.createPublisher("un publisher", "label premium");
        HttpRequest request = HttpRequest.builder().path(PublisherApiController.PUBLISHERS).path(PublisherApiController.ID_ID)
                .expandPath(id).body(null).put();
        HttpException exception = assertThrows(HttpException.class, () -> new Client().submit(request));
        assertEquals(HttpStatus.BAD_REQUEST, exception.getHttpStatus());
    }
    @Test
    void testUpdatePublisherWithoutPublisherName() {
        HttpRequest request = HttpRequest.builder().path(PublisherApiController.PUBLISHERS).path(PublisherApiController.ID_ID)
                .expandPath("invalid-id").body(new PublisherDto(null, null)).put();
        HttpException exception = assertThrows(HttpException.class, () -> new Client().submit(request));
        assertEquals(HttpStatus.BAD_REQUEST, exception.getHttpStatus());
    }

}
