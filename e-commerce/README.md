# 🛒 E-Commerce Management System

A console-based E-Commerce backend application built with **Java**, **Hibernate (JPA)**, and **PostgreSQL**. The system supports two roles — **Admin** and **User** — with full product, cart, order, payment, and address management capabilities.

---

## 📌 Table of Contents

- [Features](#features)
- [Tech Stack](#tech-stack)
- [Project Structure](#project-structure)
- [Database Configuration](#database-configuration)
- [Getting Started](#getting-started)
- [Application Flow](#application-flow)
- [Entities](#entities)
- [Admin Operations](#admin-operations)
- [User Operations](#user-operations)

---

## ✨ Features

### 👤 User
- Register & Login with role-based authentication
- Manage profile (view, update, delete account)
- Manage multiple delivery addresses (add, update, delete)
- Add/remove products from cart
- Place orders from cart with address and payment method
- Track order status
- Cancel orders

### 🔐 Admin
- Manage users (view by ID, view all)
- Manage products (add, update, delete, set out-of-stock, assign category)
- Manage product categories (add, update, delete)
- Manage orders (view, track, delete cancelled orders)
- Manage payments (view, filter by user, delete)

---

## 🛠 Tech Stack

| Layer | Technology |
|---|---|
| Language | Java |
| ORM | Hibernate 6.6 (Jakarta JPA 3.0) |
| Database | PostgreSQL 42.7 |
| Build Tool | Maven |
| Boilerplate Reduction | Lombok 1.18 |
| Interface | Console (CLI) |

---

## 📁 Project Structure

```
e-commerce/
├── src/
│   └── main/
│       ├── java/
│       │   ├── Driver/
│       │   │   └── e_comDriver.java          # Main entry point
│       │   ├── Entity/
│       │   │   ├── User.java
│       │   │   ├── Product.java
│       │   │   ├── ProductCategory.java
│       │   │   ├── Cart.java
│       │   │   ├── Order.java
│       │   │   ├── OrderTracking.java
│       │   │   ├── Payment.java
│       │   │   ├── Address.java
│       │   │   └── joinentities/
│       │   │       ├── CartItem.java
│       │   │       └── OrderItem.java
│       │   ├── IRepositories/
│       │   │   ├── IUserRepository.java
│       │   │   ├── IProductRepository.java
│       │   │   ├── ICartRepository.java
│       │   │   ├── IOrderRepository.java
│       │   │   ├── IPaymentRepository.java
│       │   │   ├── IAddressRepository.java
│       │   │   └── ICategoryRepository.java
│       │   ├── repositories/
│       │   │   └── (Implementations of all IRepositories)
│       │   └── utils/
│       │       ├── EComUtil.java             # Shared input helpers
│       │       ├── AdminUtil.java            # Admin menu & actions
│       │       ├── UserUtil.java             # User menu & actions
│       │       └── JPAUtil.java              # EntityManager factory
│       └── resources/
│           └── META-INF/
│               └── persistence.xml           # JPA/Hibernate config
└── pom.xml
```

---

## 🗄 Database Configuration

The application connects to a **PostgreSQL** database. Update the credentials in `src/main/resources/META-INF/persistence.xml`:

```xml
<property name="jakarta.persistence.jdbc.url"      value="jdbc:postgresql://localhost:5432/eComDb" />
<property name="jakarta.persistence.jdbc.user"     value="postgres" />
<property name="jakarta.persistence.jdbc.password" value="your_password" />
```

> **Note:** Create a PostgreSQL database named `eComDb` before running the application.  
> Hibernate is configured with `hbm2ddl.auto=update`, so all tables are auto-created/updated on startup.

---

## 🚀 Getting Started

### Prerequisites

- Java 17+
- Maven 3.6+
- PostgreSQL 13+

### Steps

```bash
# 1. Clone the repository
git clone https://github.com/equilisahil/Java-Projects.git
cd Java-Projects/e-commerce

# 2. Create the database
psql -U postgres -c "CREATE DATABASE eComDb;"

# 3. Update DB credentials in persistence.xml

# 4. Build the project
mvn clean compile

# 5. Run the application
mvn exec:java -Dexec.mainClass="Driver.e_comDriver"
```

---

## 🔄 Application Flow

```
Application Start
      │
      ▼
 Select Role
 ┌────┴────┐
 ▼         ▼
Admin     User
 │         │
Login    Login / Register
 │         │
 ▼         ▼
Admin    User Menu
Menu      ├── Manage Profile
├── Manage Users      │    ├── View / Update
├── Manage Products   │    └── Manage Addresses
├── Manage Orders     ├── Manage Cart
├── Manage Payments   │    ├── Add / Remove Products
└── Manage Categories └── Manage Orders
                           ├── Place Order
                           ├── Track Order
                           └── Cancel Order
```

---

## 🧩 Entities

| Entity | Description |
|---|---|
| `User` | Stores user info with role (`admin` / `user`) |
| `Product` | Product name, price, description, stock status, category |
| `ProductCategory` | Category that groups products |
| `Cart` | One-to-one with User; holds CartItems |
| `CartItem` | Join entity between Cart and Product with quantity |
| `Order` | Linked to User, Address, Payment; holds OrderItems |
| `OrderItem` | Join entity between Order and Product with quantity |
| `OrderTracking` | Tracks the status/stage of an order |
| `Payment` | Payment method and status (including refund support) |
| `Address` | Delivery address linked to a User |

---

## 🔐 Admin Operations

| Module | Actions |
|---|---|
| **Users** | View by ID, View all |
| **Products** | Add, Update, Delete, View by ID/All/Category, Set Out-of-Stock, Assign Category |
| **Product Categories** | Add, Update, Delete, View by ID, View all |
| **Orders** | View by ID, View all, View by User, Track, Delete (cancelled) |
| **Payments** | View by ID, View by User, View all, Delete |

---

## 👤 User Operations

| Module | Actions |
|---|---|
| **Profile** | View, Update, Delete Account |
| **Addresses** | Add, Update, Delete, View all |
| **Cart** | Add product (with quantity), Remove product, View cart |
| **Orders** | Place order (from cart), View orders, Track order, Cancel order |

---

## 📝 Notes

- On first run, the application automatically prompts to create an **Admin** account if none exists.
- The `plan` file in resources contains notes on a planned **payment refund** feature tied to order cancellation.
- Password field is currently stored as plain text in the `User` entity — consider hashing with BCrypt for production use.

---
