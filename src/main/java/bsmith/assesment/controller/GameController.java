package bsmith.assesment.controller;

import bsmith.assesment.dto.GameResponseDto;
import bsmith.assesment.entity.Card;
import bsmith.assesment.service.GameService;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/games/{gameId}")
@Slf4j
@AllArgsConstructor
public class GameController {

    private final GameService gameService;

    @PostMapping("/deal")
    public ResponseEntity<GameResponseDto> deal(@PathVariable @NotNull Long gameId) {
        Card card = gameService.deal(gameId);
        GameResponseDto response = GameResponseDto.builder()
                .gameId(gameId)
                .card(card.toString())
                .message("Card dealt")
                .build();
        log.info("Response for /deal: {}", response);
        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    @PostMapping("/return")
    public ResponseEntity<GameResponseDto> returnCardToBottom(@PathVariable Long gameId,
            @RequestBody Card card) {

        gameService.returnCardToBottom(gameId, card);
        GameResponseDto response = GameResponseDto.builder()
                .gameId(gameId)
                .card(card.toString())
                .message("Card returned to bottom")
                .build();

        log.info("Response for /return: {}", response);
        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    @PostMapping("/shuffle")
    public ResponseEntity<GameResponseDto> shuffle(@PathVariable Long gameId) {
        gameService.shuffle(gameId);
        GameResponseDto response = GameResponseDto.builder()
                .gameId(gameId)
                .message("Deck shuffled")
                .build();
        log.info("Response for /shuffle: {}", response);
        return new ResponseEntity<>(response, HttpStatus.OK);

    }
}