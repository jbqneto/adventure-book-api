# Adventure Book API

Backend REST API for an interactive "choose your own adventure" book system.

This application allows players to start an adventure, navigate through sections, make choices, and track their progress including health and outcomes.

---

## Tech Stack

* Java 21
* Spring Boot
* Spring Data JPA + H2 Database (in-memory)
* Maven

---

## Objectives

* 1 - Create book with validation
* 2 - List all existing books and allow for searching by title, author, category or difficulty
* 3 - Retrieve a book’s details and allow for adding/removing categories from a book
* 4 - Allow to read a book and jump between sections.
* 5 - Handle the consequences mechanism for a player
* 6 - Allow for different players each with its own progress (save, stop/pause) [optional]
* 7 - Allow for adding new books to the collection [optional]

## Features

* List/Create/Delete Category
* List/Create/Delete Player
* Create Books with Sections, options and consequences
* Add/Remove Category from Book
* Start an adventure (player + book)
* [-] Navigate through sections by choosing options
* [-] Apply consequences (e.g. lose health)
* [-] Track player progress
* [-] Detect end of game (FINISHED / DEAD)

---

## How to Build

Make sure you have:

* Java 21+
* Maven 3.9+

Run:

```bash
mvn clean install
```

---

## How to Run

```bash
mvn spring-boot:run
```

The application will start on:

```text
http://localhost:8080
```

---

## Database

This project uses **H2 in-memory database**.

### H2 Console

Available at:

```text
http://localhost:8080/h2-console
```

Use the following configuration:

```
JDBC URL: jdbc:h2:mem:testdb  
User: sa  
Password: (empty)  
```

---

## API Overview

http://localhost:8080/swagger-ui/index.html

---

## Running Tests

```bash
mvn test
```

---

## Future Improvements

* Add pagination to book listing
* Add authentication (Spring Security)
* Support PostgreSQL profile
* Improve validation layer
* Add tests to AdventureService
* Improve tests

---
