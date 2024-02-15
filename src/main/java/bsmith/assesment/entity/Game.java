package bsmith.assesment.entity;

import bsmith.assesment.enums.Rank;
import bsmith.assesment.enums.Suit;
import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import lombok.Data;

@Entity
@Table(name = "game")
@Data
public class Game {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @OneToMany(cascade = CascadeType.ALL, orphanRemoval = true)
    @JoinColumn(name = "game_id")
    private List<Card> cards = new ArrayList<>();

    public Game() {
        initialize();
        shuffle();
    }

    private void initialize() {
        Arrays.stream(Suit.values())
                .flatMap(suit -> Arrays.stream(Rank.values()).map(rank -> new Card(suit, rank)))
                .forEach(cards::add);
    }

    public Card deal() {
        return cards.remove(0);
    }

    public void returnCardToBottom(Card card) {
        cards.add(card);
    }

    public void shuffle() {
        Collections.shuffle(cards);
    }

}