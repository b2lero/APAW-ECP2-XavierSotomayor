package api.businessController;

import api.daos.DaoFactory;
import api.dtos.AlbumDto;
import api.entities.Album;

public class AlbumBusinessController {

    public String create(AlbumDto albumDto){
        Album album = new Album(albumDto.getName(), null);
        DaoFactory.getFactory().getAlbumDao().save(album);
        return album.getId();
    }

}
