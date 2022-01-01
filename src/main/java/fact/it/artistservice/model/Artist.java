package fact.it.artistservice.model;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.UUID;

@Document(collection = "artists")
public class Artist {
    @Id
    private String id;
    private String name;
    private UUID uuid;

    public Artist(UUID uuid, String name) {
        this.uuid = uuid;
        this.name = name;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {this.id = id;}

    public UUID getUuid() {
        return uuid;
    }

    public void setUuid(UUID uuid) {
        this.uuid = uuid;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
