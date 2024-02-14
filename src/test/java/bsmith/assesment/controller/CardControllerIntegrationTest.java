package bsmith.assesment.controller;

import bsmith.assesment.enums.Value;
import bsmith.assesment.model.Card;
import bsmith.assesment.enums.Suit;
import bsmith.assesment.service.CardService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.reactive.server.WebTestClient;
import org.springframework.web.reactive.function.BodyInserters;

import static org.mockito.Mockito.*;


@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class CardControllerIntegrationTest {

    @Autowired
    private WebTestClient webTestClient;

    @MockBean
    private CardService cardService;

    @BeforeEach
    public void setUp() {
        when(cardService.deal(anyString())).thenReturn(new Card(Suit.HEARTS, Value.ACE));
    }

    @Test
    public void testDeal() {
        webTestClient.post()
                .uri("/game/{gameId}/deck/deal", "game1")
                .exchange()
                .expectStatus().isOk()
                .expectBody(Card.class);

        verify(cardService, times(1)).deal("game1");
    }

    @Test
    public void testReturnCardToBottom() {
        Card card = new Card(Suit.HEARTS, Value.ACE);
        webTestClient.post()
                .uri("/game/{gameId}/deck/return", "game1")
                .contentType(MediaType.APPLICATION_JSON)
                .body(BodyInserters.fromValue(card))
                .exchange()
                .expectStatus().isOk();

        verify(cardService, times(1)).returnCardToBottom(eq("game1"), any(Card.class));
    }

    @Test
    public void testShuffle() {
        webTestClient.post()
                .uri("/game/{gameId}/deck/shuffle", "game1")
                .exchange()
                .expectStatus().isOk();

        verify(cardService, times(1)).shuffle("game1");
    }
}