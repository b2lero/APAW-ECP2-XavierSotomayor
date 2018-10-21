package api.apiControllers;

import api.businessController.AlbumBusinessController;
import api.dtos.AlbumDto;
import api.exceptions.ArgumentNotValidException;

public class AlbumApiController {

    public static final String ALBUMS = "/albums";

    private AlbumBusinessController albumBusinessController =  new AlbumBusinessController();

    public String create(AlbumDto albumDto){
        this.validate(albumDto, "albumDto");
        this.validate(albumDto.getName(),"albumDto name");
        return this.albumBusinessController.create(albumDto);
    }

    private void validate(Object property, String message) {
        if (property == null) {
            throw new ArgumentNotValidException(message + " is NULL");
        }
    }

}
