import bsmith.assesment.entity.Deck;
import jakarta.persistence.*;

import java.util.HashMap;
import java.util.Map;
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
    private Map<String, Deck> games = new HashMap<>();
}