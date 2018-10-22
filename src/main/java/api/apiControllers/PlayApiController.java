package api.apiControllers;

import api.businessController.PlayBusinessController;
import api.dtos.PlayDto;
import api.exceptions.ArgumentNotValidException;

public class PlayApiController {

    public static final String PLAYS = "/plays";

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

}
