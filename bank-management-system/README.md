# Bank Management System 🏦

A console-based **Bank Management System** built with **Java**, **Hibernate ORM**, and **PostgreSQL**. This project demonstrates a clean layered architecture using the **DAO design pattern** to perform full CRUD operations on Banks, Branches, Customers, Accounts, and Loans.

---

## Tech Stack

| Technology | Version | Purpose |
|---|---|---|
| Java | 17+ | Core language |
| Maven | 3.x | Build & dependency management |
| Hibernate ORM | 6.6.13.Final | ORM / database persistence |
| PostgreSQL | 42.7.3 (JDBC) | Relational database |
| Lombok | 1.18.30 | Boilerplate reduction |
| Jakarta Persistence | 3.0 | JPA annotations |

---

## Project Architecture

```
BMSDriver (UI / Console)
     │
     ▼
DAO Interfaces          ← BankDAO, BranchDAO, CustomerDAO, AccountDAO, LoanDAO
     │
     ▼
Service Classes         ← BankService, BranchService, CustomerService, AccountService, LoanService
     │                    (implements DAO using Hibernate EntityManager)
     ▼
DTO / Entity Classes    ← Bank, Branch, Customer, Account, Loan
     │                    (@Entity, @OneToMany, @ManyToOne, @ManyToMany)
     ▼
PostgreSQL Database     ← Tables auto-created by Hibernate (hbm2ddl.auto=update)
```

### Package Structure

```
src/main/java/
├── Driver/
│   └── BMSDriver.java          ← Entry point, Scanner-based console menu
├── DAO/
│   ├── BankDAO.java
│   ├── BranchDAO.java
│   ├── CustomerDAO.java
│   ├── AccountDAO.java
│   └── LoanDAO.java
├── Service/
│   ├── BankService.java
│   ├── BranchService.java
│   ├── CustomerService.java
│   ├── AccountService.java
│   └── LoanService.java
└── DTO/
    ├── Bank.java
    ├── Branch.java
    ├── Customer.java
    ├── Account.java
    └── Loan.java

src/main/resources/
├── META-INF/
│   └── persistence.xml         ← Hibernate + DB config
└── Mappings.txt
```

---

## Entity Relationships

```
Bank ──────────────< Branch          (One Bank has many Branches)
Bank ──────────────< Customer        (One Bank has many Customers)
Branch ────────────< Loan            (One Branch offers many Loans)
Customer ──────────< Account         (One Customer has many Accounts)
Customer >─────────< Loan            (Many-to-Many: Customer applies for Loans)
```

The `Customer ↔ Loan` many-to-many relationship is handled by a Hibernate-generated join table in PostgreSQL.

---

## Features

- **Bank Management** — Add, update, delete, and view banks (by ID, by branch, or all)
- **Branch Management** — Add branches to banks, CRUD operations with cascade delete
- **Customer Management** — Register customers with auto-linked accounts, CRUD + loan application
- **Account Management** — Manage multiple accounts per customer, unique sequence-based account numbers
- **Loan Management** — Add loan offers to branches, customers can apply for any available loan

---

## Prerequisites

- Java 17 or higher
- Apache Maven 3.x
- PostgreSQL 13 or higher

---

## Setup & Installation

### 1. Clone the repository

```bash
git clone https://github.com/ArpitKharche123/Basic-Projects.git
cd Basic-Projects/bank-management-system
```

### 2. Create the PostgreSQL database

Open `psql` or pgAdmin and run:

```sql
CREATE DATABASE bmsDb;
```

### 3. Configure database credentials

Open `src/main/resources/META-INF/persistence.xml` and update the password if needed:

```xml
<property name="jakarta.persistence.jdbc.url"      value="jdbc:postgresql://localhost:5432/bmsDb" />
<property name="jakarta.persistence.jdbc.user"     value="postgres" />
<property name="jakarta.persistence.jdbc.password" value="your_password_here" />
```

> Hibernate will auto-create all tables on first run — no SQL schema script needed.

### 4. Build the project

```bash
mvn clean install
```

### 5. Run the application

```bash
mvn exec:java -Dexec.mainClass="Driver.BMSDriver"
```

---

## Running in an IDE

| IDE | Steps |
|---|---|
| IntelliJ IDEA | `File → Open` → select `bank-management-system` folder → Maven auto-detected → run `BMSDriver.java` |
| Eclipse | `File → Import → Existing Maven Projects` → browse to `bank-management-system` |
| VS Code | Open folder → install Java Extension Pack → run `BMSDriver.java` |

---

## Usage — Console Menu

```
***** Welcome to Bank Management System *****
Select one of the following:
1. Manage Banks
2. Manage Branches
3. Manage Customers
4. Manage Accounts
5. Manage Loans
6. Exit
```

Each option leads to a sub-menu with Add / Update / View / Delete operations.

### Example flow — adding a new customer

```
3 → Manage Customers
1 → Add new Customer
  Enter name:    Arpit Kharche
  Enter phone:   9876543210
  Enter address: Pune, Maharashtra
  Enter account type: Savings
  Enter bank id: 1
→ Customer added! Customer ID: 1, Account No: 812173523
```

---

## Key Design Decisions

**DAO Pattern** — Interfaces separate *what* operations exist from *how* they're implemented. The Driver only knows about the `BankDAO` interface, not `BankService` — enabling easy swaps without changing the UI layer.

**Hibernate EntityManager** — Every Service method follows: `begin transaction → operation → commit → close`, with `rollback` on exception and `em.close()` in `finally`.

**Cascade Delete** — `Bank` cascades removal to `Branch` and `Customer`. Deleting a `Branch` first manually clears the Customer-Loan join table to avoid foreign key constraint violations, then cascades to its `Loan` offers.

**Sequence-based Account Numbers** — `Account` uses a custom `@SequenceGenerator` starting at `812173523` with `allocationSize=11` to mimic realistic bank account number generation.

**Lombok** — `@Data` generates getters/setters/toString; `@NoArgsConstructor` satisfies Hibernate's requirement for a no-arg constructor.

---

## Dependencies (`pom.xml`)

```xml
<dependencies>
    <dependency>
        <groupId>org.postgresql</groupId>
        <artifactId>postgresql</artifactId>
        <version>42.7.3</version>
    </dependency>
    <dependency>
        <groupId>org.hibernate</groupId>
        <artifactId>hibernate-core</artifactId>
        <version>6.6.13.Final</version>
    </dependency>
    <dependency>
        <groupId>org.projectlombok</groupId>
        <artifactId>lombok</artifactId>
        <version>1.18.30</version>
    </dependency>
</dependencies>
```

---

## Possible Improvements

- [ ] Add Spring Boot REST API layer
- [ ] Add JWT-based authentication
- [ ] Write JUnit + Mockito unit tests
- [ ] Integrate HikariCP connection pooling
- [ ] Add custom exception classes
- [ ] Build a web UI (React / Thymeleaf)

---

## License

This project is open source and available under the [MIT License](LICENSE).
