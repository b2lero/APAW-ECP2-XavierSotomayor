package api.daos.memory;

import api.daos.AlbumDao;
import api.entities.Album;

import java.util.HashMap;

public class AlbumDaoMemory extends GenericDaoMemory<Album> implements AlbumDao {

    public AlbumDaoMemory(){
        super(new HashMap<>());
    }

    @Override
    public String getId(Album album) {
        return album.getId();
    }

    @Override
    public void setId(Album album, String id) {
        album.setId(id);
    }
}