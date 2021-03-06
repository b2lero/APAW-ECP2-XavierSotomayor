package api.daos;

public abstract class DaoFactory {

    private static DaoFactory factory = null;


    public static DaoFactory getFactory() {
        assert factory != null;
        return factory;
    }

    public static void setFactory(DaoFactory factory) {
        DaoFactory.factory = factory;
    }

    public abstract PublisherDao getPublisherDao();

    public abstract AlbumDao getAlbumDao();

    public abstract PlayDao getPlayDao();

}
