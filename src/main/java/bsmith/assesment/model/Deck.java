package bsmith.assesment.model;

import bsmith.assesment.enums.Suit;
import bsmith.assesment.enums.Value;
import java.util.Collections;
import java.util.LinkedList;

public class Deck {

    private final LinkedList<Card> cards = new LinkedList<>();

    public Deck() {
        initialize();
    }

    private void initialize() {
        for (Suit suit : Suit.values()) {
            for (Value value : Value.values()) {
                cards.add(new Card(suit, value));
            }
        }
    }

    public Card deal() {
        return cards.poll();
    }

    public void returnCardToBottom(Card card) {
        cards.addLast(card);
    }

    public void shuffle() {
        Collections.shuffle(cards);
    }

}

