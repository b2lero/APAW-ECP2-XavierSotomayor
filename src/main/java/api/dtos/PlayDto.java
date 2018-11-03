package api.dtos;

import api.entities.PlayInfo;

public class PlayDto {

    private String nameplay;

    private String author;

    private PlayInfo playinfo;

    public PlayDto(String nameplay, String author, PlayInfo playinfo) {
        this.nameplay = nameplay;
        this.author = author;
        this.playinfo = playinfo;
    }

    public String getNameplay() {
        return nameplay;
    }

    public void setNameplay(String nameplay) {
        this.nameplay = nameplay;
    }

    public String getAuthor() {
        return author;
    }

    public void setAuthor(String author) {
        this.author = author;
    }

    public PlayInfo getPlayinfo() {
        return playinfo;
    }

    public void setPlayinfo(PlayInfo playinfo) {
        this.playinfo = playinfo;
    }

    @Override
    public String toString() {
        return "PlayDto{" +
                "nameplay='" + nameplay + '\'' +
                ", author='" + author + '\'' +
                ", playinfo=" + playinfo +
                '}';
    }
}
