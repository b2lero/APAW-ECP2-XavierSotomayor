package api.apiControllers;

import api.businessController.PlayBusinessController;
import api.dtos.PlayDto;
import api.dtos.PlaylistDto;
import api.entities.Play;
import api.exceptions.ArgumentNotValidException;

import java.util.List;

public class PlayApiController {

    public static final String PLAYS = "/plays";

    public static final String ID_ID = "{id}";

    private PlayBusinessController playBusinessController = new PlayBusinessController();

    public String create(PlayDto playDto){
        this.validate(playDto, "playDto");
        this.validate(playDto.getNameplay(), "playDto namePlay");
        this.validate(playDto.getAuthor(), "playDto author");
        return this.playBusinessController.create(playDto);
    }

    private void validate(Object property, String message) {
        if (property == null) {
            throw new ArgumentNotValidException(message + " is NULL");
        }
    }

    public List<PlaylistDto> readAll(){
        return playBusinessController.readAll();
    }

}
