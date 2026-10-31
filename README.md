# 📚 Digital Library Management System

A Java-based Digital Library Management System developed using Hibernate and JPA. The application provides book management functionality and demonstrates important persistence concepts, including entity mapping, embedded objects, enum mapping, transaction management, and Hibernate entity lifecycle operations.

## 🚀 Features

### 📖 Book Management
- Create and save new books.
- Find books by ID.
- Update book information.
- Delete books.
- Automatically generate book identifiers.
- Retrieve and verify the final database state.

### 🏢 Publisher Address
Each book contains publisher address information, including:

- City
- Street
- Postal Code

The publisher address is stored as part of the Book entity using JPA Embeddable mapping, without creating a separate database table.

### 📦 Book Stock Status

Book availability is represented using Java Enum:

- `IN_STOCK`
- `OUT_OF_STOCK`
- `COMING_SOON`

The enum values are stored as readable strings in the database.

## 🛠️ Technologies

| Technology | Purpose |
|---|---|
| Java | Core application development |
| JPA | Object-relational mapping |
| Hibernate | JPA implementation |
| PostgreSQL | Relational database |
| SQL | Database operations |
| Git | Version control |
| GitHub | Source code hosting |

## 🏗️ Domain Model

The application uses a Book entity with the following information:

| Attribute | Description |
|---|---|
| id | Automatically generated identifier |
| title | Book title |
| ISBN | International Standard Book Number |
| publicationYear | Year of publication |
| price | Book price |
| stockStatus | Current availability status |
| publisherAddress | Embedded publisher address |

The publisher address contains:

- `city`
- `street`
- `postalCode`

## 🔌 Hibernate & JPA

JPA (Java Persistence API) provides a standard way to map Java objects to relational database tables and manage persistent data.

Hibernate is used as the JPA implementation to handle object-relational mapping and database interactions.

The project demonstrates:

- Entity mapping using `@Entity`.
- Primary key generation strategies.
- Embedded objects using `@Embeddable` and `@Embedded`.
- Enum mapping using `@Enumerated(EnumType.STRING)`.
- Persistence operations using `EntityManager`.
- Database interaction through Hibernate.

## 🔄 Transaction Management

Database operations are managed using transactions to ensure data consistency.

The application uses reusable transaction management to avoid duplicating transaction-handling code across persistence operations.

The persistence infrastructure is initialized once and reused during application execution instead of being unnecessarily recreated.

## 🧬 Hibernate Entity Lifecycle

An important part of this project is demonstrating Hibernate entity lifecycle states and understanding how entity management affects database changes.

The lifecycle exercise covers:

**1. Transient**
- Create a new Book object that is not managed by Hibernate.

**2. Persistent**
- Save the Book within a transaction.
- Modify one of its fields while it is managed.
- Observe how Hibernate tracks changes through dirty checking.

**3. Detached**
- Detach the entity from Hibernate management.
- Modify the detached object.
- Observe that these changes are not automatically synchronized with the database.

**4. Reattachment**
- Make the detached object managed again.
- Save the final modifications through the appropriate persistence operation.
- Verify the resulting database state.

This exercise demonstrates the difference between managed and detached entities and how Hibernate handles changes during transaction execution.

## 🧪 Application Testing

The main application demonstrates the following operations:

1. Create at least three different books.
2. Save all books in the database.
3. Retrieve a book by its ID.
4. Modify the retrieved book information.
5. Save the changes.
6. Delete one book.
7. Verify the final database state.

A separate entity lifecycle test demonstrates transient, persistent, and detached states.

## 🎯 Learning Objectives

This project demonstrates practical knowledge of:

- Object-Relational Mapping (ORM).
- JPA and Hibernate.
- EntityManager.
- Entity Lifecycle Management.
- Transient, Persistent, and Detached States.
- Dirty Checking.
- Embeddable Value Objects.
- Enum Mapping.
- Primary Key Generation Strategies.
- Transaction Management.
- Repository Pattern.
- Database Persistence.

---

**Developed with ☕ Java and ❤️**
