package bsmith.assesment.controller;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.any;
import static org.mockito.Mockito.anyLong;
import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import bsmith.assesment.dto.GameResponseDto;
import bsmith.assesment.entity.Card;
import bsmith.assesment.entity.Game;
import bsmith.assesment.service.GameService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.http.ResponseEntity;

public class GameControllerTest {

    @Mock
    private GameService gameService;

    private GameController gameController;

    @BeforeEach
    public void setUp() {
        MockitoAnnotations.openMocks(this);
        gameController = new GameController(gameService);
    }

    @Test
    public void testCreateGame() {
        Game game = new Game();
        when(gameService.createGame()).thenReturn(game);

        ResponseEntity<GameResponseDto> response = gameController.createGame();

        assertEquals(201, response.getStatusCodeValue());
        verify(gameService, times(1)).createGame();
    }

    @Test
    public void testDeal() {
        Card card = new Card();
        when(gameService.deal(anyLong())).thenReturn(card);

        ResponseEntity<GameResponseDto> response = gameController.deal(1L);

        assertEquals(200, response.getStatusCodeValue());
        verify(gameService, times(1)).deal(anyLong());
    }

    @Test
    public void testReturnCardToBottom() {
        Card card = new Card();
        doNothing().when(gameService).returnCardToBottom(anyLong(), any(Card.class));

        ResponseEntity<GameResponseDto> response = gameController.returnCardToBottom(1L, card);

        assertEquals(200, response.getStatusCodeValue());
        verify(gameService, times(1)).returnCardToBottom(anyLong(), any(Card.class));
    }

    @Test
    public void testShuffle() {
        doNothing().when(gameService).shuffle(anyLong());

        ResponseEntity<GameResponseDto> response = gameController.shuffle(1L);

        assertEquals(200, response.getStatusCodeValue());
        verify(gameService, times(1)).shuffle(anyLong());
    }
}