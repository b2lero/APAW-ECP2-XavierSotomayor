package api.dtos;

public class PlayDto {

    private String nameplay;

    private String author;

    public PlayDto(String nameplay, String author) {
        this.nameplay = nameplay;
        this.author = author;
    }

    public String getAuthor() {
        return author;
    }

    public void setAuthor(String author) {
        this.author = author;
    }

    public String getNameplay() {
        return nameplay;
    }

    public void setNameplay(String nameplay) {
        this.nameplay = nameplay;
    }


}
