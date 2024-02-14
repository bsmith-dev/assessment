# Online Card Games Web Service Overview

## Introduction

In response to recent shelter at home orders, ACME Corp., a subsidiary of Castle Rock, is planning to expand its product line by adding online card games. To facilitate this expansion, a proof of concept web service application will be developed. This document outlines the proposal for creating a general-purpose web service in Java, aimed at modeling the dealing of cards from a deck for use in various online card games.

## Objectives

The primary objective of this web service is to provide a reliable and flexible backend that can handle card game operations. The service will offer the following functionalities:

- **Deal one card from the top of the deck:** Allows players to draw the next available card.
- **Return one card to the bottom of the deck:** Enables players to place a card back into the deck.
- **Shuffle the deck:** Ensures that the deck's order is randomized, mimicking the real-life shuffling of cards.
- **Optional bonus:** Support operations for concurrent games, each using their own unique decks.

## Technical Specifications

- **Deck Composition:** The service will use a standard 52-card deck, including four suits (hearts, diamonds, clubs, spades) with cards numbering from 2 through 10, and including Jack, Queen, King, Ace.
- **Data Interchange Format:** JSON will be used for data interchange to ensure compatibility and ease of integration with various clients.
- **State Persistence:** In-memory state persistence will be implemented, negating the need for a database or external persistence layer.
- **Concurrency Support:** (Optional) The service will aim to support multiple games concurrently, each operating with its own deck.

## Development Considerations

- **Framework:** While a solution using Spring Boot is preferred for its simplicity and rapid development capabilities, flexibility in framework choice is allowed to accommodate developer expertise. This could include alternatives such as Quarkus or Micronaut.
- **Modeling Cards and Decks:** Cards and decks will be modeled in a manner that supports the operations outlined above. This includes creating appropriate classes and methods to handle dealing, returning, and shuffling cards.
- **Delivery Format:** The final product will be delivered as either a WAR or executable JAR file, allowing for easy deployment and testing.

## Submission Requirements

- **Source Code:** Complete source code for the web service, organized and documented in a manner that supports readability and maintainability.
- **Build and Run Instructions:** Detailed instructions on how to build and run the solution, including any prerequisites or dependencies.
- **Assumptions:** A list of any assumptions made during the development process.

## Conclusion

This proposal outlines the development of a web service for ACME Corp. to support online card games. By providing a flexible and robust service for dealing, returning, and shuffling cards, ACME Corp. will be well-positioned to launch its new online card game offerings. We look forward to developing this exciting addition to our product line.
