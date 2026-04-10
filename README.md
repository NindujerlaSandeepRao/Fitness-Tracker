
# Fitness Tracking Backend

A production-grade backend system for managing user fitness activities, authentication, and personalized recommendations. The application is built using Spring Boot with a focus on clean architecture, secure API design, and scalability.

---

## Overview

This project provides RESTful APIs for:

- User registration and authentication
- Activity tracking and management
- Recommendation generation based on activities

The system follows a layered architecture and uses JWT-based stateless authentication to ensure secure communication between client and server.

---

## Architecture

The application follows a standard layered architecture:

Client → Controller → Service → Repository → Database  
                      ↓  
                Security Layer (JWT Filter)

- Controllers handle HTTP requests and responses
- Services implement business logic
- Repositories manage database operations using Spring Data JPA
- Security layer validates and extracts user identity from JWT

---

## Modules

### Authentication Module

Handles user registration and login.

Endpoints:
- POST `/api/auth/register`
- POST `/api/auth/login`

On successful login, a JWT token is generated using the user ID and role.

Authentication logic is implemented in the service layer :contentReference[oaicite:0]{index=0}.

---

### Activity Module

Manages fitness activities for users.

Endpoints:
- POST `/api/activities`
- GET `/api/activities`

User identity is extracted from the security context rather than client input to prevent spoofing :contentReference[oaicite:1]{index=1}.

Activity persistence and mapping are handled in the service layer :contentReference[oaicite:2]{index=2}.

---

### Recommendation Module

Generates and retrieves recommendations based on user activities.

Endpoints:
- POST `/api/recommendation/generate`
- GET `/api/recommendation/user/{userId}`
- GET `/api/recommendation/activity/{activityId}`

Business logic is implemented in the recommendation service :contentReference[oaicite:3]{index=3}.

---

## Data Model

### User

- id (UUID)
- email (unique)
- password (hashed)
- firstName
- lastName
- role (USER, ADMIN)
- createdAt
- updatedAt

### Activity

- id (UUID)
- user (Many-to-One)
- type (ENUM)
- duration
- caloriesBurned
- additionalMetrics (JSON)
- startTime
- createdAt
- updatedAt

### Recommendation

- id (UUID)
- user (Many-to-One)
- activity (Many-to-One)
- recommendation
- improvements (JSON)
- suggestions (JSON)
- safety (JSON)
- createdAt
- updatedAt

---

## Relationships

- One user can have multiple activities
- One user can have multiple recommendations
- One activity can have multiple recommendations

---

## Security

The application uses JWT-based authentication.

- Token is generated during login
- Token contains:
  - userId (subject)
  - roles
- Every request must include:

Authorization: Bearer <token>

A custom JWT filter intercepts requests, validates tokens, and sets authentication in the security context :contentReference[oaicite:4]{index=4}.

Security configuration is defined in :contentReference[oaicite:5]{index=5}.

---

## Technology Stack

Backend:
- Java 17
- Spring Boot
- Spring Security
- Spring Data JPA

Database:
- PostgreSQL

Security:
- JSON Web Tokens (JJWT)

Documentation:
- Swagger/OpenAPI :contentReference[oaicite:6]{index=6}

Build Tool:
- Maven

---

## Key Design Decisions

### Stateless Authentication

The system does not store session data. All authentication is handled using JWT, making the system scalable and suitable for distributed environments.

### Secure User Context

User identity is never taken from request payloads or headers. It is always extracted from the JWT stored in the security context.

### JSON Columns

Flexible fields such as additionalMetrics, suggestions, and improvements are stored using JSON columns in the database.

---

## Repository Layer

Custom queries are implemented using Spring Data JPA:

- Activity queries by user ID :contentReference[oaicite:7]{index=7}  
- Recommendation queries by user and activity :contentReference[oaicite:8]{index=8}  
- User lookup by email :contentReference[oaicite:9]{index=9}  

---

## Configuration

Environment variables are used for database configuration:

spring.datasource.url=${DB_URL}

spring.datasource.username=${DB_USER}

spring.datasource.password=${DB_PASSWORD}


---

## Running the Application

1. Clone the repository

---

## Running the Application

1. Clone the repository

git clone <repository-url>


2. Set environment variables
   
DB_URL=jdbc:postgresql://<host>:5432/<database>?sslmode=require

DB_USER=<username>

DB_PASSWORD=<password>

3. Run the application

./mvnw spring-boot:run


---

## API Documentation

Swagger UI is available at:

http://localhost:8080/swagger-ui/index.html


---

## Future Enhancements

- Migration to microservices architecture
- Event-driven communication (Kafka)
- Redis caching layer
- AI-based recommendation engine
- Frontend integration

---

## Author

Sandeep Rao

---

## Summary

This project demonstrates:

- Secure API design using JWT
- Clean layered architecture
- Real-world database modeling
- Scalable backend development practices
