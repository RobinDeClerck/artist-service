package fact.it.artistservice.controller;

import fact.it.artistservice.model.Artist;
import fact.it.artistservice.repository.ArtistRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.annotation.PostConstruct;
import java.util.List;

@RestController
public class ArtistRestController {
    @Autowired
    private ArtistRepository artistRepository;

    @PostConstruct
    public void fillDB() {
        if(artistRepository.count()==0) {
            artistRepository.save(new Artist("9e0e2b01-41db-4008-bd8b-988977d6019a", "The Police"));
            artistRepository.save(new Artist("aa62b28e-b6d4-4086-91d4-e5fac1ed56f3","Royal Blood"));
            artistRepository.save(new Artist("9c9f1380-2516-4fc9-a3e6-f9f61941d090","Muse"));
            artistRepository.save(new Artist("8bfac288-ccc5-448d-9573-c33ea2aa5c30","Red Hot Chili Peppers"));
            artistRepository.save(new Artist("ea4dfa26-f633-4da6-a52a-f49ea4897b58","R.E.M."));
            artistRepository.save(new Artist("a74b1b7f-71a5-4011-9441-d0b5e4122711","Radiohead"));
        }
    }

    @GetMapping("/artists")
    public List<Artist> getArtists() {
        return this.artistRepository.findAll();
    }

//    @GetMapping("/artists/{name}")
//    public Artist getArtistByName(@PathVariable String name) {
//        return this.artistRepository.findArtistByName(name);
//    }

    @GetMapping("/artists/{uuid}")
    public Artist getArtistByUuid(@PathVariable String uuid) {
        return this.artistRepository.findArtistByUuid(uuid);
    }
}
