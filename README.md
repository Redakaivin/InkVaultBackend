# InkVault Backend

This repository contains the backend service for InkVault, a secure personal entry management application. It handles authentication, data persistence, and the core logic for managing user entries.

## Tech Stack

The service is built with the following:
- Java 17
- Spring Boot 4.0.2
- Spring Security with JWT for authentication
- Spring Data JPA for persistence
- MySQL for the database

## Core Features

- User authentication using JWT (Register and Login).
- Full CRUD operations for personal entries.
- Entry locking system to prevent simultaneous edits.
- Secured API endpoints using custom JWT filters.

## Project Structure

The project follows a standard Spring Boot architecture:
- controller: API endpoints for authentication and entry management.
- service: Business logic layer.
- repository: Data access layer using Spring Data JPA.
- model: Database entities (User, Entry, etc.).
- security: Configuration for JWT and security filters.

## API Endpoints

### Authentication
- POST `/auth/register`: Create a new user account.
- POST `/auth/login`: Authenticate and receive a JWT token.

### Entries
- GET `/api/entries`: Retrieve all entries for the logged-in user.
- GET `/api/entries/{id}`: Get specific entry details.
- POST `/api/entries`: Create a new entry.
- PUT `/api/entries/{id}`: Update an existing entry.
- POST `/api/entries/{id}/lock`: Lock an entry for editing.

## Setup and Installation

1. Clone this repository.
2. Ensure you have a MySQL database running.
3. Configure your database credentials in `src/main/resources/application.properties`.
4. Run the application using Maven:
   ```bash
   mvn spring-boot:run
   ```

## Frontend Repository

The frontend side of this project, built with React, can be found here:
[InkVault Frontend](https://github.com/Redakaivin/InkVault)
