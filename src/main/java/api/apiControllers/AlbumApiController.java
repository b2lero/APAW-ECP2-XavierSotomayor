package api.apiControllers;

import api.businessController.AlbumBusinessController;
import api.dtos.AlbumDto;
import api.entities.Play;
import api.exceptions.ArgumentNotValidException;

import java.util.List;

public class AlbumApiController {

    public static final String ALBUMS = "/albums";

    public static final String ID_ID = "/{id}";

    public static final String PLAYS = "/plays";

    private AlbumBusinessController albumBusinessController =  new AlbumBusinessController();

    public String create(AlbumDto albumDto){
        this.validate(albumDto, "albumDto");
        this.validate(albumDto.getName(),"albumDto name");
        return this.albumBusinessController.create(albumDto);
    }

    public void updatePlay(String albumId, List<Play> play){
        this.validate(albumId, "albumdto id");
        this.albumBusinessController.updatePlay(albumId, play);
    }

    private void validate(Object property, String message) {
        if (property == null) {
            throw new ArgumentNotValidException(message + " is NULL");
        }
    }

}
