package api.dtos;

import api.entities.Play;

import java.util.List;

public class AlbumDto {

    private String name;

    private List<Play> playslist;

    public AlbumDto(String name) {
        this.name = name;
    }

    public AlbumDto(String name, List<Play> playList){
        this.name = name;
        this.playslist =  playslist;
    }

    public String getName() {
        return this.name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public List<Play> getPlays() {
        return playslist;
    }

    public void setPlays(List<Play> playlist) {
        this.playslist = playlist;
    }

    @Override
    public String toString() {
        return "AlbumDto{" +
                "name='" + name + '\'' +
                ", playslist=" + playslist +
                '}';
    }
}
