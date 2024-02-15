package bsmith.assesment.service;

import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.any;
import static org.mockito.Mockito.anyLong;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import bsmith.assesment.entity.Card;
import bsmith.assesment.entity.Game;
import bsmith.assesment.exception.GameException;
import bsmith.assesment.repository.CardRepository;
import bsmith.assesment.repository.GameRepository;
import java.util.Optional;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

public class GameServiceTest {

    @Mock
    private GameRepository gameRepository;

    @Mock
    private CardRepository cardRepository;

    private GameService gameService;

    @BeforeEach
    public void setUp() {
        MockitoAnnotations.openMocks(this);
        gameService = new GameService(gameRepository, cardRepository);
    }

    @Test
    public void testDeal() {
        Game game = new Game();
        when(gameRepository.findById(anyLong())).thenReturn(Optional.of(game));

        Card card = gameService.deal(1L);

        assertNotNull(card);
        verify(gameRepository, times(1)).findById(anyLong());

    }

    @Test
    public void testReturnCardToBottom() {
        Game game = new Game();
        when(gameRepository.findById(anyLong())).thenReturn(Optional.of(game));

        Card dealtCard = gameService.deal(1L);

        gameService.returnCardToBottom(1L, dealtCard);

        verify(gameRepository, times(2)).findById(anyLong());
        verify(gameRepository, times(1)).save(any(Game.class));
    }

    @Test
    public void testReturnCardToBottom_GameException() {
        when(gameRepository.findById(anyLong())).thenReturn(Optional.empty());

    }

    @Test
    public void testShuffle() {
        assertThrows(GameException.class, () -> gameService.returnCardToBottom(1L, new Card()));
    }

    @Test
    public void testShuffle_GameException() {
        when(gameRepository.findById(anyLong())).thenReturn(Optional.empty());

        assertThrows(GameException.class, () -> gameService.shuffle(1L));
    }
}