package api.dtos;

public class AlbumDto {

    private String name;

    public AlbumDto(String name) {
        this.name = name;
    }

    public String getName() {
        return this.name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Override
    public String toString() {
        return "AlbumDto{" +
                "name='" + name + '\'' +
                '}';
    }
}
