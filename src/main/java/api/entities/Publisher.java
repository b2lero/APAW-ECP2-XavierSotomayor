package api.entities;

public class Publisher{
    private String id;
    private String name;
    private String label;

    private Album[] AlbumList;

    public Publisher(String id, String name) {
        this.id = id;
        this.name = name;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getLabel() {
        return label;
    }

    public void setLabel(String label) {
        this.label = label;
    }

    public Album[] getAlbumList() {
        return AlbumList;
    }

    public void setAlbumList(Album[] albumList) {
        AlbumList = albumList;
    }

    @Override
    public String toString() {
        return "Publisher{" +
                "id='" + id + '\'' +
                ", name='" + name + '\'' +
                ", label='" + label + '\'' +
                '}';
    }
}