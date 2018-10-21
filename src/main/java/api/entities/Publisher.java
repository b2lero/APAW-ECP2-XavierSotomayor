package api.entities;

public class Publisher{
    private String id;
    private String name;
    private String label;

    private Album[] albumList;

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
        return albumList;
    }

    public void setAlbumList(Album[] albumList) {
        this.albumList = albumList;
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