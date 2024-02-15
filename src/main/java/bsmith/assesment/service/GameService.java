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

/**
 * This service class is responsible for managing the game logic. It interacts with the
 * GameRepository and CardRepository to persist game state.
 */
@Service
@Slf4j
@RequiredArgsConstructor
@Transactional
public class GameService {

    private final GameRepository gameRepository;
    private final CardRepository cardRepository;

    /**
     * Creates a new game and saves it in the repository.
     *
     * @return The created Game object.
     */
    public Game createGame() {
        return gameRepository.save(new Game());
    }

    /**
     * Deals a card from the deck in the specified game.
     *
     * @param gameId The ID of the game from which to deal a card.
     * @return The Card object that was dealt.
     * @throws GameException if the game has not been started yet or if there are no more cards in
     *                       the deck.
     */
    public Card deal(Long gameId) {
        log.info("Deal game id: {}", gameId);
        Game game = gameRepository.findById(gameId).orElseThrow(() -> new GameException(
                "Game has not been started yet. Please create a game first."));

        Card card;
        try {
            card = game.deal();
        } catch (IndexOutOfBoundsException e) {
            throw new GameException("There are no more cards in the deck.");
        }

        removeCardFromDeck(card.getId());
        return card;
    }

    /**
     * Returns a card to the bottom of the deck in the specified game.
     *
     * @param gameId The ID of the game to which to return the card.
     * @param card   The Card object to return to the deck.
     * @throws GameException if the game has not been started yet or if the card is already in the
     *                       deck.
     */
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

    /**
     * Shuffles the deck in the specified game.
     *
     * @param gameId The ID of the game to shuffle.
     * @throws GameException if the game has not been started yet.
     */
    public void shuffle(Long gameId) {
        log.info("Shuffle game id: {}", gameId);
        Game game = gameRepository.findById(gameId).orElseThrow(() -> new GameException(
                "Game has not been started yet. Please deal a card first."));

        game.shuffle();
        gameRepository.save(game);
    }

    /**
     * Removes a card from the deck in the specified game.
     *
     * @param gameId The ID of the game from which to remove the card.
     */
    private void removeCardFromDeck(Long gameId) {
        log.info("Remove card from deck game id: {}", gameId);
        cardRepository.deleteById(gameId);
    }

}