package api.dtos;

import api.entities.Play;

public class PlaylistDto {

    private String id;

    private String namePlay;


    public PlaylistDto(Play play){
        this.id = play.getId();
        this.namePlay = play.getNamePlay();
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getNamePlay() {
        return namePlay;
    }

    public void setNamePlay(String namePlay) {
        this.namePlay = namePlay;
    }

}
