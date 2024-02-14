package bsmith.assesment.model;

import bsmith.assesment.enums.Suit;
import bsmith.assesment.enums.Value;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class Card {
    private Suit suit;
    private Value value;
}

