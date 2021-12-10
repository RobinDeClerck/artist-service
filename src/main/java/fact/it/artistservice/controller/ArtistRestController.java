package fact.it.artistservice.controller;

import fact.it.artistservice.model.Artist;
import fact.it.artistservice.repository.ArtistRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api")
public class ArtistRestController {
    @Autowired
    private ArtistRepository artistRepository;

    @GetMapping("/artists")
    public List<Artist> getArtists() {
        return this.artistRepository.findAll();
    }

    @PostMapping("/artist")
    public Artist createArtist(@RequestBody Artist artist) {
        return artistRepository.save(artist);
    }
}
