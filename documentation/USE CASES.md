# Use Case Document

## Actors
- **Player:** Interacts with the game by dealing and returning cards, and shuffling the deck.
- **Game Administrator:** Manages game sessions and ensures that multiple games can run concurrently.

## Use Cases

### 1. Deal a Card

- **Primary Actor:** Player
- **Goal:** The player receives the top card from the deck.
- **Preconditions:** The deck is initialized and contains cards.
- **Postconditions:** The player has one less card in the deck.
- **Main Flow:**
    1. The player requests to deal a card from a specific game's deck.
    2. The system removes the top card from the deck and returns it to the player.
- **Extensions:**
    - If the deck is empty, the system notifies the player that no more cards can be dealt.

### 2. Return a Card to the Bottom of the Deck

- **Primary Actor:** Player
- **Goal:** The player returns a card to the bottom of the deck.
- **Preconditions:** The player has a card to return.
- **Postconditions:** The deck has one more card at its bottom.
- **Main Flow:**
    1. The player selects a card to return to the deck.
    2. The system places the selected card at the bottom of the deck.
- **Extensions:**
    - If the card does not belong to the deck, the system rejects the action.

### 3. Shuffle the Deck

- **Primary Actor:** Player
- **Goal:** The deck is shuffled to randomize the order of cards.
- **Preconditions:** The deck is initialized and contains cards.
- **Postconditions:** The order of cards in the deck is randomized.
- **Main Flow:**
    1. The player requests to shuffle the deck.
    2. The system randomizes the order of cards in the deck.

### 4. Support Concurrent Games

- **Primary Actor:** Game Administrator
- **Goal:** Enable multiple games to be played concurrently, each with its own deck.
- **Preconditions:** There are players for multiple games.
- **Postconditions:** Each game session has its own deck that can be independently managed.
- **Main Flow:**
    1. The game administrator initializes a new game session with a unique identifier.
    2. Players in different sessions can deal, return, and shuffle cards without affecting other games.
