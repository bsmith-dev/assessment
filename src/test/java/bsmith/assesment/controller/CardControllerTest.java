package bsmith.assesment.controller;

import bsmith.assesment.model.Card;
import bsmith.assesment.service.GameService;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
@AutoConfigureMockMvc
public class CardControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private GameService gameService;

    private ObjectMapper objectMapper = new ObjectMapper();

    @BeforeEach
    public void setUp() {
        when(gameService.deal(anyString())).thenReturn(new Card());
    }

    @Test
    public void testDeal() throws Exception {
        mockMvc.perform(post("/games/{gameId}/deal", "game1"))
                .andExpect(status().isOk());
    }

    @Test
    public void testReturnCardToBottom() throws Exception {
        Card card = new Card();

        mockMvc.perform(post("/games/{gameId}/return", "game1")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(card)))
                .andExpect(status().isOk());
    }

    @Test
    public void testShuffle() throws Exception {
        mockMvc.perform(post("/games/{gameId}/shuffle", "game1"))
                .andExpect(status().isOk());
    }
}