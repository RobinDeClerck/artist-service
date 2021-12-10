package fact.it.artistservice.controller;

import fact.it.artistservice.model.Artist;
import fact.it.artistservice.repository.ArtistRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.annotation.PostConstruct;
import java.util.List;

@RestController
@RequestMapping("/api")
public class ArtistRestController {
    @Autowired
    private ArtistRepository artistRepository;

    @PostConstruct
    public void fillDB() {
        if(artistRepository.count()==0) {
            artistRepository.save(new Artist("The Police"));
            artistRepository.save(new Artist("Royal Blood"));
            artistRepository.save(new Artist("Muse"));
            artistRepository.save(new Artist("Red Hot Chili Peppers"));
            artistRepository.save(new Artist("R.E.M."));
            artistRepository.save(new Artist("Radiohead"));
        }
    }

//    @PostMapping("/artist")
//    public Artist createArtist(@RequestBody Artist artist) {
//        return artistRepository.save(artist);
//    }

    @GetMapping("/artists")
    public List<Artist> getArtists() {
        return this.artistRepository.findAll();
    }

    @GetMapping("/artists/{name}")
    public Artist getArtistByName(@PathVariable String name) {
        return this.artistRepository.findArtistByName(name);
    }
}
