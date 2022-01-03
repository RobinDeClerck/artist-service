package fact.it.artistservice.controller;

import fact.it.artistservice.model.Artist;
import fact.it.artistservice.repository.ArtistRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.annotation.PostConstruct;
import java.util.Arrays;
import java.util.List;

@RestController
public class ArtistRestController {
    @Autowired
    private ArtistRepository artistRepository;

    @PostConstruct
    public void fillDB() {
        if(artistRepository.count()==0) {
            artistRepository.save(new Artist("9e0e2b01-41db-4008-bd8b-988977d6019a", "The Police", "Rock band", "United Kingdom", Arrays.asList("Sting", "Stewart Copeland", "Andy Summers", "Henry Padovani"), "https://i.scdn.co/image/ab67618600001016af496a5f2377f1149d2a5cf3"));
            artistRepository.save(new Artist("aa62b28e-b6d4-4086-91d4-e5fac1ed56f3","Royal Blood", "Rock duo", "United Kingdom", Arrays.asList("Mike Kerr", "Ben Thatcher"), "https://i.scdn.co/image/ab676186000010164ecf014fa786e9c5dfffe37c"));
            artistRepository.save(new Artist("9c9f1380-2516-4fc9-a3e6-f9f61941d090","Muse", "Rock band", "United Kingdom", Arrays.asList("Matt Bellamy", "Chris Wolstenholme", "Dominic Howard"), "https://i.scdn.co/image/ab67618600001016ef59f1c62339f247d38ded80"));
            artistRepository.save(new Artist("8bfac288-ccc5-448d-9573-c33ea2aa5c30","Red Hot Chili Peppers", "Rock band", "California", Arrays.asList("Anthony Kiedis", "John Frusciante", "Dave Navarro", "Chad Smith", "Flea", "Josh Klinghoffer", "Hillel Slovak", "Jack Irons", "Jack Sherman", "Cliff Martinez", "Arik Marshall", "D.H. Peligro", "Jesse Tobias", "DeWayne McKnight"), "https://i.scdn.co/image/ab676186000010168de7d477c0febe421ea84332"));
            artistRepository.save(new Artist("ea4dfa26-f633-4da6-a52a-f49ea4897b58","R.E.M.", "Rock band", "Georgia", Arrays.asList("Michael Stipe", "Peter Buck", "Bill Berry", "Mike Mills"), "https://i.scdn.co/image/ab67618600001016c210c5b1c9b555891662e79f"));
            artistRepository.save(new Artist("a74b1b7f-71a5-4011-9441-d0b5e4122711","Radiohead", "Rock band", "United Kingdom", Arrays.asList("Thom Yorke", "Jonny Greenwood", "Ed O'Brien", "Colin Greenwood", "Philip Selway"),"https://i.scdn.co/image/ab676186000010161802a4cbec82e078cc15cbb0"));
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

    @GetMapping("/artists/{MBID}")
    public Artist getArtistByUuid(@PathVariable String MBID) {
        return this.artistRepository.findArtistByMBID(MBID);
    }
}
