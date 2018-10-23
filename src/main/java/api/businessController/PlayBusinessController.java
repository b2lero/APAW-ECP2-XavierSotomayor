package api.businessController;

import api.daos.DaoFactory;
import api.dtos.PlayDto;
import api.dtos.PlaylistDto;
import api.entities.Play;

import java.util.ArrayList;
import java.util.List;

public class PlayBusinessController {

    public String create(PlayDto playDto){
        Play play = new Play(playDto.getNameplay(), playDto.getAuthor());
        DaoFactory.getFactory().getPlayDao().save(play);
        return play.getId();
    }

    public List<PlaylistDto> readAll(){
        List<Play> playlist = DaoFactory.getFactory().getPlayDao().findAll();
        List<PlaylistDto> playListDtos = new ArrayList<>();
        for (Play play : playlist){
            playListDtos.add(new PlaylistDto(play));
        }
        return playListDtos;
    }

}
