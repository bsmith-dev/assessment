package bsmith.assesment.service;

import bsmith.assesment.exception.GameException;
import bsmith.assesment.model.Card;
import bsmith.assesment.model.Deck;
import java.util.HashMap;
import java.util.Map;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

@Service
@Slf4j
@Getter
@AllArgsConstructor
public class CardService {

    private Map<String, Deck> games = new HashMap<>();

    public Card deal(String gameId) {
        Deck deck = games.computeIfAbsent(gameId, k -> new Deck());
        return deck.deal();
    }

    public void returnCardToBottom(String gameId, Card card) {
        log.info("Game id: {}", gameId);
        // games.get(gameId) != null && games.get(gameId) != null &&
        if (games.get(gameId) == null) {
            String MESSAGE = "Game has not been started yet. Please deal a card first.";
            log.info(MESSAGE);
            throw new GameException(MESSAGE);
        }
        if (games.get(gameId).getCards().contains(card)) {
            String MESSAGE = "Card "+ card +" is already in deck";
            log.info(MESSAGE);
            throw new GameException(MESSAGE);
        }

        Deck deck = games.get(gameId);
        if (deck != null) {
            deck.returnCardToBottom(card);
        }


    }

    public void shuffle(String gameId) {
        Deck deck = games.get(gameId);
        if (deck != null) {
            deck.shuffle();
        }
    }
}
