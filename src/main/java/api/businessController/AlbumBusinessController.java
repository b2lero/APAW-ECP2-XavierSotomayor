package api.businessController;

import api.daos.DaoFactory;
import api.dtos.AlbumDto;
import api.dtos.PlayDto;
import api.entities.Album;
import api.entities.Play;
import api.exceptions.NotFoundException;

import java.util.List;

public class AlbumBusinessController {

    public String create(AlbumDto albumDto){
        Album album = new Album(albumDto.getName(), null,null);
        DaoFactory.getFactory().getAlbumDao().save(album);
        return album.getId();
    }

    public void updatePlay(String albumId, List<Play> playList){
        Album album = DaoFactory.getFactory().getAlbumDao().read(albumId)
                .orElseThrow(()-> new NotFoundException("Album id: " + albumId));
        album.setPlaylist(playList);
        DaoFactory.getFactory().getAlbumDao().save(album);
    }

}
