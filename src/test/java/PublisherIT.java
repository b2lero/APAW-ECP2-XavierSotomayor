import api.apiControllers.PublisherApiController;
import api.daos.DaoFactory;
import api.daos.memory.DaoMemoryFactory;
import api.dtos.PublisherDto;
import http.Client;
import http.HttpException;
import http.HttpRequest;
import http.HttpStatus;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

public class PublisherIT {

    @BeforeEach
    void before() {
        DaoFactory.setFactory(new DaoMemoryFactory());
    }

    @Test
    void testCreateUser() {
        this.createPublisher();
    }

    private String createPublisher() {
        HttpRequest request = HttpRequest.builder(PublisherApiController.PUBLISHERS).body(new PublisherDto("publisher")).post();
        return (String) new Client().submit(request).getBody();
    }

    @Test
    void testPublisherInvalidRequest() {
        HttpRequest request = HttpRequest.builder(PublisherApiController.PUBLISHERS + "/invalid").body(null).post();
        HttpException exception = assertThrows(HttpException.class, () -> new Client().submit(request));
        assertEquals(HttpStatus.BAD_REQUEST, exception.getHttpStatus());
    }

    @Test
    void testCreatePublisherWithoutPublisherDto() {
        HttpRequest request = HttpRequest.builder(PublisherApiController.PUBLISHERS).body(null).post();
        HttpException exception = assertThrows(HttpException.class, () -> new Client().submit(request));
        assertEquals(HttpStatus.BAD_REQUEST, exception.getHttpStatus());
    }

    @Test
    void testCreatePublisherWithoutPublisherDtoName() {
        HttpRequest request = HttpRequest.builder(PublisherApiController.PUBLISHERS).body(new PublisherDto(null)).post();
        HttpException exception = assertThrows(HttpException.class, () -> new Client().submit(request));
        assertEquals(HttpStatus.BAD_REQUEST, exception.getHttpStatus());
    }

}
