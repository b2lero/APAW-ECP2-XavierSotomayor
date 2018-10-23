package api.daos.memory;

import api.daos.AlbumDao;
import api.daos.DaoFactory;
import api.daos.PlayDao;
import api.daos.PublisherDao;

public class DaoMemoryFactory extends DaoFactory {

    private PublisherDao publisherDao;

    private AlbumDao albumDao;

    private PlayDao playDao;

    @Override
    public PublisherDao getPublisherDao() {
        if (publisherDao == null){
            publisherDao = new PublisherDaoMemory();
        }
        return publisherDao;
    }

    @Override
    public AlbumDao getAlbumDao() {
        if (albumDao == null){
            albumDao = new AlbumDaoMemory();
        }
        return albumDao;
    }

    @Override
    public PlayDao getPlayDao() {
        if(playDao == null){
            playDao = new PlayDaoMemory();
        }
        return playDao;
    }
}
