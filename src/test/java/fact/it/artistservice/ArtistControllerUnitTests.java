package fact.it.artistservice;

import com.fasterxml.jackson.databind.ObjectMapper;
import fact.it.artistservice.model.Artist;
import fact.it.artistservice.repository.ArtistRepository;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import java.util.ArrayList;
import java.util.List;

import static org.hamcrest.Matchers.is;
import static org.hamcrest.collection.IsCollectionWithSize.hasSize;
import static org.mockito.BDDMockito.given;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@SpringBootTest
@AutoConfigureMockMvc
public class ArtistControllerUnitTests {
    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private ArtistRepository artistRepository;

    private ObjectMapper mapper = new ObjectMapper();

    @Test
    public void givenArtist_whenGetArtistByName_thenReturnJsonReview() throws Exception {
        Artist artist1 = new Artist("The Police");

        given(artistRepository.findArtistByName("The Police")).willReturn(artist1);

        mockMvc.perform(get("/api/artists/{name}","The Police"))
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.name", is("The Police")));
    }

    @Test
    public void whenGetArtists_thenReturnJsonArtists() throws Exception {
        Artist artist1 = new Artist("The Police");
        Artist artist2 = new Artist("Royal Blood");
        Artist artist3 = new Artist("Radiohead");

        List<Artist> artistList = new ArrayList<>();
        artistList.add(artist1);
        artistList.add(artist2);
        artistList.add(artist3);

        given(artistRepository.findAll()).willReturn(artistList);

        mockMvc.perform(get("/api/artists"))
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$", hasSize(3)))
                .andExpect(jsonPath("$[0].name",is("The Police")))
                .andExpect(jsonPath("$[1].name",is("Royal Blood")))
                .andExpect(jsonPath("$[2].name",is("Radiohead")));
    }


}
