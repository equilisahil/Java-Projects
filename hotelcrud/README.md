# 🏨 Hotel CRUD — Spring Boot REST API

A simple **Spring Boot REST API** for managing hotel records, built with **Spring Data JPA**, **PostgreSQL**, and **Lombok**. Includes integrated **Swagger/OpenAPI** documentation.

---

## 📌 Table of Contents

- [Features](#features)
- [Tech Stack](#tech-stack)
- [Project Structure](#project-structure)
- [Database Configuration](#database-configuration)
- [Getting Started](#getting-started)
- [API Endpoints](#api-endpoints)
- [Sample Request/Response](#sample-requestresponse)
- [API Documentation (Swagger)](#api-documentation-swagger)

---

## ✨ Features

- Create a new hotel record (`name`, `rating`)
- Fetch a hotel record by its ID
- DTO-based request handling to keep entity layer decoupled from API layer
- Auto-managed database schema via Hibernate (`ddl-auto=update`)
- Interactive API docs via Swagger UI (Springdoc OpenAPI)

---

## 🛠 Tech Stack

| Layer | Technology |
|---|---|
| Language | Java 21 |
| Framework | Spring Boot 3.5.9 |
| Data Layer | Spring Data JPA / Hibernate |
| Database | PostgreSQL |
| Boilerplate Reduction | Lombok |
| API Docs | Springdoc OpenAPI (Swagger UI) |
| Build Tool | Maven |

---

## 📁 Project Structure

```
hotelcrud/
├── src/
│   ├── main/
│   │   ├── java/edu/j2ee/hotelcrud/
│   │   │   ├── HotelcrudApplication.java      # Spring Boot entry point
│   │   │   ├── controller/
│   │   │   │   └── HotelController.java       # REST endpoints
│   │   │   ├── service/
│   │   │   │   └── HotelService.java          # Business logic
│   │   │   ├── repository/
│   │   │   │   └── HotelRepository.java       # JPA repository
│   │   │   ├── entity/
│   │   │   │   └── Hotel.java                 # Hotel entity
│   │   │   ├── dto/
│   │   │   │   └── HotelDTO.java               # Data transfer object
│   │   │   └── helpers/
│   │   │       └── Mapper.java                 # Entity ↔ DTO mapper
│   │   └── resources/
│   │       └── application.properties          # App & DB config
│   └── test/
│       └── java/edu/j2ee/hotelcrud/
│           └── HotelcrudApplicationTests.java
└── pom.xml
```

---

## 🗄 Database Configuration

The app connects to **PostgreSQL**. Update `src/main/resources/application.properties` with your own credentials:

```properties
spring.datasource.url=jdbc:postgresql://localhost:5432/postgres
spring.datasource.username=postgres
spring.datasource.password=your_password

spring.jpa.hibernate.ddl-auto=update
spring.jpa.show-sql=true
spring.jpa.properties.hibernate.dialect=org.hibernate.dialect.PostgreSQLDialect
```

> ⚠️ **Security note:** The committed `application.properties` currently contains a hardcoded DB username/password. Before pushing publicly, replace it with placeholder values (or externalize secrets via environment variables) to avoid exposing real credentials in your GitHub repo.

---

## 🚀 Getting Started

### Prerequisites
- Java 21+
- Maven 3.6+
- PostgreSQL 13+

### Steps

```bash
# 1. Clone the repository
git clone https://github.com/equilisahil/Java-Projects.git
cd Java-Projects/hotelcrud

# 2. Ensure PostgreSQL is running and a database exists
psql -U postgres -c "CREATE DATABASE postgres;"   # or your own DB name

# 3. Update DB credentials in application.properties

# 4. Build the project
./mvnw clean compile

# 5. Run the application
./mvnw spring-boot:run
```

The application starts at: **http://localhost:8080**

---

## 📡 API Endpoints

| Method | Endpoint | Description |
|---|---|---|
| `GET` | `/hotel/{id}` | Fetch a hotel by its ID |
| `POST` | `/hotel` | Create a new hotel |

---

## 🔁 Sample Request/Response

### Create a Hotel
**Request:** `POST /hotel`
```json
{
  "name": "Taj Palace",
  "rating": 4.8
}
```

**Response:**
```json
{
  "id": 1,
  "name": "Taj Palace",
  "rating": 4.8
}
```

### Get a Hotel by ID
**Request:** `GET /hotel/1`

**Response:**
```json
{
  "id": 1,
  "name": "Taj Palace",
  "rating": 4.8
}
```

---

## 📘 API Documentation (Swagger)

Since **Springdoc OpenAPI** is included, interactive API docs are available once the app is running at:

```
http://localhost:8080/swagger-ui.html
```

or

```
http://localhost:8080/swagger-ui/index.html
```

---

## 🧭 Future Improvements

- Add `PUT`/`PATCH` endpoint to update existing hotel records
- Add `DELETE` endpoint to remove a hotel
- Add `GET /hotel` to fetch all hotels
- Add input validation (`@Valid`, `@NotBlank`, `@Min`/`@Max` for rating)
- Add centralized exception handling (e.g. `@ControllerAdvice`) for missing hotel IDs
- Externalize DB credentials using environment variables / `.env`

---

## 👨‍💻 Author

**Sahil Marbate**  
[GitHub Profile](https://github.com/equilisahil)
