package api.businessController;

import api.daos.DaoFactory;
import api.dtos.PublisherDto;
import api.entities.Publisher;
import api.exceptions.NotFoundException;

public class PublisherBusinessController {

    public String create(PublisherDto publisherDto){
        Publisher publisher = new Publisher(publisherDto.getName(),publisherDto.getLabel(),null);
        DaoFactory.getFactory().getPublisherDao().save(publisher);
        return publisher.getId();
    }

    public void updateName(String id, PublisherDto publisherDto){
        Publisher publisher = DaoFactory.getFactory().getPublisherDao().read(id)
                .orElseThrow(() -> new NotFoundException("Publisher id: " + id + "not found"));
        publisher.setName(publisherDto.getName());
        DaoFactory.getFactory().getPublisherDao().save(publisher);
    }
}


