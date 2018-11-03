package api.entities;

public class Play{

    private String id;
    private String namePlay ;
    private String author;
    private PlayInfo playinfo;

    public Play(String namePlay, String author, PlayInfo playinfo) {
        this.id =id;
        this.author = author;
        this.namePlay = namePlay;
        this.playinfo = playinfo;
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
        return "Play{" +
                "id='" + id + '\'' +
                ", namePlay='" + namePlay + '\'' +
                ", author='" + author + '\'' +
                ", playinfo=" + playinfo +
                '}';
    }
}