package api.businessController;

import api.daos.DaoFactory;
import api.dtos.PlayDto;
import api.entities.Play;

public class PlayBusinessController {

    public String create(PlayDto playDto){
        Play play = new Play(playDto.getNameplay(), playDto.getAuthor());
        DaoFactory.getFactory().getPlayDao().save(play);
        return play.getId();
    }

}
