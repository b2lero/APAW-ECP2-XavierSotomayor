package api;

import api.apiControllers.AlbumApiController;
import api.apiControllers.PlayApiController;
import api.apiControllers.PublisherApiController;
import api.dtos.AlbumDto;
import api.dtos.PlayDto;
import api.dtos.PublisherDto;
import api.entities.Play;
import api.exceptions.ArgumentNotValidException;
import api.exceptions.NotFoundException;
import api.exceptions.RequestInvalidException;
import http.HttpRequest;
import http.HttpResponse;
import http.HttpStatus;

import java.nio.file.Path;
import java.util.List;

public class Dispatcher {

    private PublisherApiController publisherApiController = new PublisherApiController();
    private AlbumApiController albumApiController = new AlbumApiController();
    private PlayApiController playApiController = new PlayApiController();


    public void submit(HttpRequest request, HttpResponse response) {
        String ERROR_MESSAGE = "{'error':'%S'}";
        try {
            switch (request.getMethod()) {
                case POST:
                    this.doPost(request, response);
                    break;
                case PUT:
                    this.doPut(request, response);
                    break;
                case GET:
                    this.doGet(request, response);
                    break;
                case PATCH:
                    this.doPatch(request,response);
                    break;
                case DELETE:
                    this.doDelete(request, response);
                    break;
                default: // Unexpected
                    throw new RequestInvalidException("method error: " + request.getMethod());
            }
        } catch (ArgumentNotValidException | RequestInvalidException exception) {
            response.setBody(String.format(ERROR_MESSAGE, exception.getMessage()));
            response.setStatus(HttpStatus.BAD_REQUEST);
        } catch (NotFoundException exception) {
            response.setBody(String.format(ERROR_MESSAGE, exception.getMessage()));
            response.setStatus(HttpStatus.NOT_FOUND);
        } catch (Exception exception) {  // Unexpected
            exception.printStackTrace();
            response.setBody(String.format(ERROR_MESSAGE, exception));
            response.setStatus(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    private void doPost(HttpRequest request, HttpResponse response) {
        if (request.isEqualsPath(PublisherApiController.PUBLISHERS)) {
            response.setBody(this.publisherApiController.create((PublisherDto) request.getBody()));
        } else if (request.isEqualsPath(AlbumApiController.ALBUMS)) {
            response.setBody(this.albumApiController.create((AlbumDto) request.getBody()));
        } else if (request.isEqualsPath(PlayApiController.PLAYS)) {
            response.setBody((this.playApiController.create((PlayDto) request.getBody())));
        } else {
            throw new RequestInvalidException("method error: " + request.getMethod());
        }
    }

    private void doPut(HttpRequest request, HttpResponse response) {
        if (request.isEqualsPath(PublisherApiController.PUBLISHERS + PublisherApiController.ID_ID)) {
            this.publisherApiController.update(request.getPath(1), (PublisherDto) request.getBody());
        }  else {
            throw new RequestInvalidException("method error: " + request.getMethod() + ' ' + request.getPath());
        }
    }

    private void doPatch(HttpRequest request, HttpResponse response){
        if (request.isEqualsPath(AlbumApiController.ALBUMS + AlbumApiController.ID_ID +AlbumApiController.PLAYS)){
            this.albumApiController.updatePlay(request.getPath(1), (List<Play>) request.getBody());
        }
    }

    private void doGet(HttpRequest request, HttpResponse response) {
        if (request.isEqualsPath(PlayApiController.PLAYS)){
            response.setBody(this.playApiController.readAll());
        } else{
            throw new RequestInvalidException("method error: " + request.getMethod() + ' ' + request.getPath());

        }
    }

    private void doDelete(HttpRequest request, HttpResponse response) {
        if (request.isEqualsPath(PlayApiController.PLAYS + PlayApiController.ID_ID)){
            this.playApiController.delete(request.getPath(1));
        }
    }
}
