package api.dtos;

public class PublisherDto {


    private String name;

    public PublisherDto(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Override
    public String toString() {
        return "PublisherDto{" +
                "name='" + name + '\'' +
                '}';
    }
}
