# Project Name

This is a standard Spring Boot project built with Maven. It includes a Swagger page for API documentation and testing, and an H2 database console for inspecting the contents.

## Documentation

The documentation for this project is spread across several markdown files:

- `README.md`: This file. It provides an overview of the project, including how to build and run it, and an overview of the available endpoints.

- `OVERVIEW.md`: Provides a high-level overview of the project, including its objectives and technical specifications.

- `PLAN.md`: Outlines the project plan, including the methodology, work breakdown structure, deliverables, and risk management.

- `USE CASES.md`: Describes the use cases that the project aims to fulfill. It includes the actors, goals, preconditions, postconditions, main flow, and extensions for each use case.

In addition to these markdown files, the code itself is documented with comments. These comments explain what each part of the code does and how it works. They are written in the Javadoc style, which means you can generate an HTML documentation website using the Javadoc tool if you wish.

## Prerequisites

- Java 8 or higher
- Maven

## Building the Project

To build the project, navigate to the project directory and run the following command:

```bash
mvn clean install
```

This will compile the code, run the tests, and create a JAR file in the `target` directory.

## Running the Project

To run the project, use the following command:

```bash
mvn spring-boot:run
```

The application will start and listen on port 8080.

## API Documentation and Testing

The application provides the following RESTful endpoints:

- `POST /games`: Creates a new game.
- `POST /games/{gameId}/deal`: Deals a card from the deck in the specified game.
- `POST /games/{gameId}/return`: Returns a card to the bottom of the deck in the specified game.
- `POST /games/{gameId}/shuffle`: Shuffles the deck in the specified game.

For more detailed information about the endpoints, including request parameters and response formats, please refer to the Swagger UI at [http://localhost:8080/swagger-ui/index.html](http://localhost:8080/swagger-ui/index.html).

## H2 Database Console

You can inspect the contents of the H2 database at:

[http://localhost:8080/h2-console](http://localhost:8080/h2-console)

Use the following credentials to log in:

- User: sa
- Password: pw
- JDBC URL: jdbc:h2:mem:game-db

