package fact.it.artistservice.model;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.List;

@Document(collection = "artists")
public class Artist {
    @Id
    private String id;
    private String name;
    private String MBID;

    private String type;
    private String originCountry;

    // https://stackoverflow.com/questions/1005073/initialization-of-an-arraylist-in-one-line
    private List<String> members;

    private String bannerImage;


    public Artist(String MBID, String name, String type, String originCountry, List<String> members, String bannerImage) {
        this.name = name;
        this.MBID = MBID;
        this.type = type;
        this.bannerImage = bannerImage;
        this.originCountry = originCountry;
        this.members = members;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {this.id = id;}

    public String getMBID() {
        return MBID;
    }

    public void setMBID(String MBID) {
        this.MBID = MBID;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getBannerImage() {
        return bannerImage;
    }

    public void setBannerImage(String bannerImage) {
        this.bannerImage = bannerImage;
    }

    public String getOriginCountry() {
        return originCountry;
    }

    public void setOriginCountry(String originCountry) {
        this.originCountry = originCountry;
    }

    public List<String> getMembers() {
        return members;
    }

    public void setMembers(List<String> members) {
        this.members = members;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }
}
