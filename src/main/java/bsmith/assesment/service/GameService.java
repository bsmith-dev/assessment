package bsmith.assesment.service;

import bsmith.assesment.entity.Card;
import bsmith.assesment.entity.Game;
import bsmith.assesment.exception.GameException;
import bsmith.assesment.repository.CardRepository;
import bsmith.assesment.repository.GameRepository;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

@Service
@Slf4j
@RequiredArgsConstructor
public class GameService {

    private final GameRepository gameRepository;
    private final CardRepository cardRepository;

    @Transactional
    public Card deal(Long gameId) {
        log.info("Deal game id: {}", gameId);
        Game game = gameRepository.findById(gameId).orElseGet(() -> {
            Game newGame = new Game();
            newGame.setId(gameId);
            gameRepository.save(newGame);
            return newGame;
        });
        return game.deal();
    }

    public void returnCardToBottom(Long gameId, Card card) {
        log.info("Game id: {} Card: {}", gameId, card);
        Game game = gameRepository.findById(gameId).orElseThrow(() -> new GameException(
                "Game has not been started yet. Please deal a card first."));

        if (game.getCards().contains(card)) {
            throw new GameException("Card " + card + " is already in deck");
        }

        game.returnCardToBottom(card);
        gameRepository.save(game);
    }

    public void shuffle(Long gameId) {
        log.info("Shuffle game id: {}", gameId);
        Game game = gameRepository.findById(gameId).orElseThrow(() -> new GameException(
                "Game has not been started yet. Please deal a card first."));

        game.shuffle();
        gameRepository.save(game);
    }
}