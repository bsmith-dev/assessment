package bsmith.assesment.controller;

import bsmith.assesment.model.Card;
import bsmith.assesment.service.CardService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/game/{gameId}/deck")
public class CardController {

    private final CardService cardService;

    @Autowired
    public CardController(CardService cardService) {
        this.cardService = cardService;
    }

    @PostMapping("/deal")
    public Card deal(@PathVariable String gameId) {
        return cardService.deal(gameId);
    }

    @PostMapping("/return")
    public void returnCardToBottom(@PathVariable String gameId, @RequestBody Card card) {
        cardService.returnCardToBottom(gameId, card);
    }

    @PostMapping("/shuffle")
    public void shuffle(@PathVariable String gameId) {
        cardService.shuffle(gameId);
    }
}
