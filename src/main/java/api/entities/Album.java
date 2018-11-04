package api.entities;

import java.time.LocalDateTime;
import java.util.List;

public class Album{

    private String id;
    private String name;
    private LocalDateTime date;

    private List<Play> playlist;

    public Album(String name, LocalDateTime date, List<Play> playList) {
        this.id = id;
        this.name = name;
        this.date = LocalDateTime.now();
        this.playlist = playList;

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

    public LocalDateTime getDate() {
        return date;
    }

    public void setDate(LocalDateTime date) {
        this.date = date;
    }

    public List<Play> getPlaylist() {
        return playlist;
    }

    public void setPlaylist(List<Play> playlist) {
        this.playlist = playlist;
    }

    @Override
    public String toString() {
        return "Album{" +
                "id='" + id + '\'' +
                ", name='" + name + '\'' +
                ", date=" + date +
                ", playlist=" + playlist +
                '}';
    }
}