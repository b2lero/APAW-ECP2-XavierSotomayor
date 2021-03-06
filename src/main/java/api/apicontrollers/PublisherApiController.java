package api.apicontrollers;

import api.businesscontrollers.PublisherBusinessController;
import api.dtos.PublisherDto;
import api.exceptions.ArgumentNotValidException;

public class PublisherApiController {

    public static final String PUBLISHERS = "/publishers";
    public static final String ID_ID = "/{id}";

    private PublisherBusinessController publisherBusinessController = new PublisherBusinessController();

    public String create(PublisherDto publisherDto){
        this.validate(publisherDto, "publisherDto");
        this.validate(publisherDto.getName(), "publisherDto name");
        return this.publisherBusinessController.create(publisherDto);
    }

    public void update(String id, PublisherDto publisherDto){
        this.validate(publisherDto, "publisher Dto");
        this.validate(publisherDto.getName(), "publisherDto name");
        this.publisherBusinessController.updateName(id, publisherDto);
    }

    private void validate(Object property, String message) {
        if (property == null) {
            throw new ArgumentNotValidException(message + " is NULL");
        }
    }


}
