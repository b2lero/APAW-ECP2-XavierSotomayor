package api.entities;

import java.util.List;

public class Publisher{
    private String id;
    private String name;
    private String label;
    private List<Album> albumList;

    public Publisher(String name, String label, List<Album> albumList) {
        this.name = name;
        this.label = label;
        this.albumList = albumList;
    }

    public void setId(String id) {
        this.id = id;
    }

    public Publisher setName(String name) {
        this.name = name;
        return this;
    }

    public void setLabel(String label) {
        this.label = label;
    }

    public void setAlbumList(List<Album> albumList) {
        this.albumList = albumList;
    }

    public String getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public String getLabel() {
        return label;
    }

    public List<Album> getAlbumList() {
        return albumList;
    }

    @Override
    public String toString() {
        return "Publisher{" +
                "id='" + id + '\'' +
                ", name='" + name + '\'' +
                ", label='" + label + '\'' +
                ", albumList=" + albumList +
                '}';
    }
}