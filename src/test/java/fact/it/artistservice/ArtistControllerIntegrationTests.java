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
import java.util.UUID;

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

    private Artist artist1 = new Artist("9e0e2b01-41db-4008-bd8b-988977d6019a","The Police");
    private Artist artist2 = new Artist("aa62b28e-b6d4-4086-91d4-e5fac1ed56f3","Royal Blood");
    private Artist artist3 = new Artist("a74b1b7f-71a5-4011-9441-d0b5e4122711","Radiohead");

    @BeforeEach
    public void beforeAllTests() {
        artistRepository.deleteAll();
        artistRepository.save(artist1);
        artistRepository.save(artist2);
        artistRepository.save(artist3);
    }

    @AfterEach
    public void afterAllTests() {
        artistRepository.deleteAll();
    }

//    @Test
//    public void givenArtist_whenGetArtistByArtistName_thenReturnJsonArtist() throws Exception {
//        mockMvc.perform(get("/artists/{name}","The Police"))
//                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
//                .andExpect(status().isOk())
//                .andExpect(jsonPath("$.name", is("The Police")));
//    }

    @Test
    public void givenArtist_whenGetArtistByArtistUuid_thenReturnJsonArtist() throws Exception {
        mockMvc.perform(get("/artists/{uuid}","9e0e2b01-41db-4008-bd8b-988977d6019a"))
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.name", is("The Police")))
                .andExpect(jsonPath("$.uuid", is("9e0e2b01-41db-4008-bd8b-988977d6019a")));
    }

    @Test
    public void givenArtist_whenGetArtists_thenReturnJsonArtists() throws Exception {

        List<Artist> artistList = new ArrayList<>();
        artistList.add(artist1);
        artistList.add(artist2);
        artistList.add(artist3);

        mockMvc.perform(get("/artists"))
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$", hasSize(3)))
                .andExpect(jsonPath("$[0].name", is("The Police")))
                .andExpect(jsonPath("$[0].uuid", is("9e0e2b01-41db-4008-bd8b-988977d6019a")))
                .andExpect(jsonPath("$[1].name", is("Royal Blood")))
                .andExpect(jsonPath("$[1].uuid", is("aa62b28e-b6d4-4086-91d4-e5fac1ed56f3")))
                .andExpect(jsonPath("$[2].name", is("Radiohead")))
                .andExpect(jsonPath("$[2].uuid", is("a74b1b7f-71a5-4011-9441-d0b5e4122711")));
    }

}
