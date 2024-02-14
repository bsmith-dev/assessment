package bsmith.assesment.service;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import bsmith.assesment.entity.Card;
import bsmith.assesment.entity.Deck;
import java.util.HashMap;
import java.util.Map;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

public class GameServiceTest {

    private GameService gameService;
    private Map<String, Deck> games;

    @BeforeEach
    public void setUp() {
        games = new HashMap<>();
        gameService = new GameService(games);
    }

    @Test
    public void testDeal() {
        String gameId = "game1";
        Deck mockDeck = mock(Deck.class);
        games.put(gameId, mockDeck);

        Card mockCard = mock(Card.class);
        when(mockDeck.deal()).thenReturn(mockCard);

        Card result = gameService.deal(gameId);

        assertEquals(mockCard, result);
        verify(mockDeck, times(1)).deal();
    }

    @Test
    public void testReturnCardToBottom() {
        String gameId = "game1";
        Deck mockDeck = mock(Deck.class);
        games.put(gameId, mockDeck);

        Card mockCard = mock(Card.class);

        gameService.returnCardToBottom(gameId, mockCard);

        verify(mockDeck, times(1)).returnCardToBottom(mockCard);
    }

    @Test
    public void testShuffle() {
        String gameId = "game1";
        Deck mockDeck = mock(Deck.class);
        games.put(gameId, mockDeck);

        gameService.shuffle(gameId);

        verify(mockDeck, times(1)).shuffle();
    }
}