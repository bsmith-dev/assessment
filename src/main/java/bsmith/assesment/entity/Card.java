package bsmith.assesment.entity;

import bsmith.assesment.enums.Rank;
import bsmith.assesment.enums.Suit;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;


@NoArgsConstructor
@AllArgsConstructor
@Table(name = "card")
@Entity
@Data
public class Card {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Enumerated(EnumType.STRING)
    @Column(name = "suit")
    private Suit suit;

    @Enumerated(EnumType.STRING)
    @Column(name = "rank")
    private Rank rank;

    @ManyToOne()
    @JoinColumn(name = "game_id")
    private Game game;

    public Card(Suit suit, Rank rank) {
        this.suit = suit;
        this.rank = rank;
    }

}

