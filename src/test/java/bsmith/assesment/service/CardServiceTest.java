package bsmith.assesment.service;

import bsmith.assesment.model.Card;
import bsmith.assesment.model.Deck;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import java.util.HashMap;
import java.util.Map;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

public class CardServiceTest {
    private CardService cardService;
    private Map<String, Deck> games;

    @BeforeEach
    public void setUp() {
        games = new HashMap<>();
        cardService = new CardService(games);
    }

    @Test
    public void testDeal() {
        String gameId = "game1";
        Deck mockDeck = mock(Deck.class);
        games.put(gameId, mockDeck);

        Card mockCard = mock(Card.class);
        when(mockDeck.deal()).thenReturn(mockCard);

        Card result = cardService.deal(gameId);

        assertEquals(mockCard, result);
        verify(mockDeck, times(1)).deal();
    }

    @Test
    public void testReturnCardToBottom() {
        String gameId = "game1";
        Deck mockDeck = mock(Deck.class);
        games.put(gameId, mockDeck);

        Card mockCard = mock(Card.class);

        cardService.returnCardToBottom(gameId, mockCard);

        verify(mockDeck, times(1)).returnCardToBottom(mockCard);
    }

    @Test
    public void testShuffle() {
        String gameId = "game1";
        Deck mockDeck = mock(Deck.class);
        games.put(gameId, mockDeck);

        cardService.shuffle(gameId);

        verify(mockDeck, times(1)).shuffle();
    }
}