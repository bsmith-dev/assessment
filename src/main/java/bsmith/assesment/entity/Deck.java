package bsmith.assesment.entity;

import bsmith.assesment.enums.Suit;
import bsmith.assesment.enums.Rank;
import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import java.util.Collections;
import java.util.LinkedList;
import lombok.Getter;
import org.springframework.stereotype.Component;

@Component
@Getter
@Table(name = "deck")
@Entity
public class Deck {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @OneToMany(cascade = CascadeType.ALL, orphanRemoval = true)
    private final LinkedList<Card> cards = new LinkedList<>();

    public Deck() {
        initialize();
        shuffle();
    }

    private void initialize() {
        for (Suit suit : Suit.values()) {
            for (Rank rank : Rank.values()) {
                cards.add(new Card(suit, rank));
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

