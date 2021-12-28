package fact.it.artistservice;

import fact.it.artistservice.model.Artist;
import fact.it.artistservice.repository.ArtistRepository;
import org.junit.jupiter.api.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import java.util.ArrayList;
import java.util.List;

import static org.hamcrest.Matchers.is;
import static org.hamcrest.collection.IsCollectionWithSize.hasSize;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@SpringBootTest
@AutoConfigureMockMvc
public class ArtistControllerIntegrationTests {

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private ArtistRepository artistRepository;

    private Artist artist1 = new Artist("The Police");
    private Artist artist2 = new Artist("Royal Blood");
    private Artist artist3 = new Artist("Radiohead");
//    private Artist artistToBeDeleted = new Artist("Muse");

    @BeforeEach
    public void beforeAllTests() {
        artistRepository.deleteAll();
        artistRepository.save(artist1);
        artistRepository.save(artist2);
        artistRepository.save(artist3);
//        artistRepository.save(artistToBeDeleted);
    }

    @AfterEach
    public void afterAllTests() {
        artistRepository.deleteAll();
    }

    @Test
    public void givenArtist_whenGetArtistByArtistName_thenReturnJsonReview() throws Exception {
        mockMvc.perform(get("/api/artists/{name}","The Police"))
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.name", is("The Police")));
    }

    @Test
    public void givenArtist_whenGetArtists_thenReturnJsonReviews() throws Exception {

//        List<Artist> artistList = new ArrayList<>();
//        artistList.add(artist1);
//        artistList.add(artist2);
//        artistList.add(artist3);
//        artistList.add(artistToBeDeleted);

        mockMvc.perform(get("/api/artists"))
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$", hasSize(4)))
                .andExpect(jsonPath("$[0].name", is("The Police")))
                .andExpect(jsonPath("$[1].name", is("Royal Blood")))
                .andExpect(jsonPath("$[2].name", is("Radiohead")))
                .andExpect(jsonPath("$[3].name", is("Muse")));
    }

}
