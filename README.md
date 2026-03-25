# Adventure Book API

Backend REST API for an interactive "choose your own adventure" book system.

This application allows players to start an adventure, navigate through sections, make choices, and track their progress including health and outcomes.

---

## 🚀 Tech Stack

* Java 21
* Spring Boot
* Spring Data JPA + H2 Database (in-memory)
* Maven

---

## Objectives

* 0 - Create book with validation
* 1 - List all existing books and allow for searching by title, author, category or difficulty
* 2 - Retrieve a book’s details and allow for adding/removing categories from a book
* 3 - Allow to read a book and jump between sections.
* 4 - Handle the consequences mechanism for a player
* 5 - Allow for different players each with its own progress (save, stop/pause) [optional]
* 6 - Allow for adding new books to the collection [optional]

## Features

* List/Create/Delete Category
* List/Create/Delete Player
* Create Books with Sections, options and consequences
* Add/Remove Category from Book
[] Start an adventure (player + book)
[] Navigate through sections by choosing options
[] Apply consequences (e.g. lose health)
[] Track player progress
[] Detect end of game (FINISHED / DEAD)

---

## 🛠️ How to Build

Make sure you have:

* Java 21+
* Maven 3.9+

Run:

```bash
mvn clean install
```

---

## ▶️ How to Run

```bash
mvn spring-boot:run
```

The application will start on:

```text
http://localhost:8080
```

---

## 🧪 Database

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

## 📡 API Overview

### 📚 Books

#### Create Book

```http
POST /api/books
```

#### List Books (with filters)

```http
GET /api/books?title=&author=&category=&difficulty=
```

#### Get Book Details

```http
GET /api/books/{id}
```

---

### 👤 Players

#### Create Player

```http
POST /api/players
```

#### Delete Player

```http
DELETE /api/players/{id}
```

---

### 🏷️ Categories

#### List Categories

```http
GET /api/categories
```

#### Create Category

```http
POST /api/categories
```

#### Delete Category

```http
DELETE /api/categories/{name}
```

---

### 🎮 Adventures (Core Feature)

#### Start Adventure

```http
POST /api/adventures
```

Body:

```json
{
  "playerId": 1,
  "bookId": 1
}
```

---

#### Get Adventure State

```http
GET /api/adventures/{id}
```

---

#### Choose Option

```http
POST /api/adventures/{id}/choices
```

Body:

```json
{
  "optionId": 10
}
```

---

## 🧠 Business Rules

* A book must contain:

    * Exactly one BEGIN section
    * At least one ENDING section
* Player starts with **10 health**
* Choosing an option may:

    * Move to another section
    * Apply consequences (e.g. lose health)
* If health reaches 0 → status = **DEAD**
* If reaching ENDING → status = **FINISHED**
* Once finished or dead → no further actions allowed

---

## 🧩 Project Structure

```
api/
  controller/
  dto/

domain/
  model/
  enumtype/

repository/

service/

config/
bootstrap/
```

---

## 🧪 Running Tests

```bash
mvn test
```

---

## 📌 Notes

* This project follows a **DDD-inspired layered architecture**
* Business logic is implemented in services
* Controllers are thin (no business rules)
* MapStruct is used for DTO mapping
* Relationships are handled explicitly to avoid JPA pitfalls

---

## 💡 Future Improvements

* Add pagination to book listing
* Add authentication (Spring Security)
* Support PostgreSQL profile
* Add Swagger/OpenAPI documentation
* Improve validation layer

---

## 👨‍💻 Author

Developed as part of a backend engineering challenge.
