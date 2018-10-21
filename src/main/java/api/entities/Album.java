package api.entities;

import java.time.LocalDateTime;

public class Album{

    private String id;
    private String name;
    private LocalDateTime date;

    private Play[] playlist;

    public Album(String name, LocalDateTime date) {
        this.id = id;
        this.name = name;
        this.date = LocalDateTime.now();
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

    public Play[] getPlaylist() {
        return playlist;
    }

    public void setPlaylist(Play[] playlist) {
        this.playlist = playlist;
    }

    public LocalDateTime getDate() {
        return date;
    }

    public void setDate(LocalDateTime date) {
        this.date = date;
    }

}