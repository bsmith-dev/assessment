package bsmith.assesment.controller;

import bsmith.assesment.model.Card;
import bsmith.assesment.enums.Suit;
import bsmith.assesment.enums.Value;
import bsmith.assesment.service.CardService;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import static org.mockito.Mockito.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
@AutoConfigureMockMvc
public class CardControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private CardService cardService;

    private ObjectMapper objectMapper = new ObjectMapper();

    @BeforeEach
    public void setUp() {
        when(cardService.deal(anyString())).thenReturn(new Card(Suit.HEARTS, Value.ACE));
    }

    @Test
    public void testDeal() throws Exception {
        mockMvc.perform(post("/game/{gameId}/deck/deal", "game1"))
                .andExpect(status().isOk());

        verify(cardService, times(1)).deal("game1");
    }

    @Test
    public void testReturnCardToBottom() throws Exception {
        Card card = new Card(Suit.HEARTS, Value.ACE);

        mockMvc.perform(post("/game/{gameId}/deck/return", "game1")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(card)))
                .andExpect(status().isOk());

        verify(cardService, times(1)).returnCardToBottom(eq("game1"), any(Card.class));
    }

    @Test
    public void testShuffle() throws Exception {
        mockMvc.perform(post("/game/{gameId}/deck/shuffle", "game1"))
                .andExpect(status().isOk());

        verify(cardService, times(1)).shuffle("game1");
    }
}