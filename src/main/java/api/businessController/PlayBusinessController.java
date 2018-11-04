package api.businessController;

import api.daos.DaoFactory;
import api.dtos.PlayDto;
import api.dtos.PlaylistDto;
import api.entities.Play;

import java.util.ArrayList;
import java.util.List;

public class PlayBusinessController {

    public String create(PlayDto playDto){
        Play play = new Play(playDto.getNameplay(), playDto.getAuthor(), playDto.getPlayinfo());
        DaoFactory.getFactory().getPlayDao().save(play);
        return play.getId();
    }

    public void delete(String id){
        DaoFactory.getFactory().getPlayDao().deleteById(id);
    }

    public List<PlaylistDto> readAll(){
        List<Play> playlist = DaoFactory.getFactory().getPlayDao().findAll();
        List<PlaylistDto> playListDtos = new ArrayList<>();
        for (Play play : playlist){
            playListDtos.add(new PlaylistDto(play));
        }
        return playListDtos;
    }

    public List<PlaylistDto> findByName(String namePlayid){
        List<Play> playlist = DaoFactory.getFactory().getPlayDao().findAll();
        List<PlaylistDto>  playlistDtos = new ArrayList<>();
        for (Play play : playlist){
            if (play.getId().equals(namePlayid))
                playlistDtos.add(new PlaylistDto(play));
        }

        return playlistDtos;
    }


}
