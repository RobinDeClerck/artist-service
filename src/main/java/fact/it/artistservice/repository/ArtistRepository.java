package fact.it.artistservice.repository;

import fact.it.artistservice.model.Artist;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface ArtistRepository extends MongoRepository<Artist, String> {

    Artist findArtistByName(String name);
    Artist findArtistByUuid(String uuid);

}
