# AirlineAPI

**AirlineAPI** is a RESTful web application designed to streamline the management of flights, tickets, bookings, and user interactions in an airline ecosystem. Built with **Spring Boot**, this application adheres to **SOLID principles** and clean code standards, ensuring maintainability and scalability. The project includes robust security mechanisms and a modular architecture.

---

## 🚀 Features

- **Authentication & Authorization**: Secure JWT-based authentication with role-based access (USER, ADMIN).
- **Flight Management**: Add, view, and manage flights, including querying available flights.
- **Ticket Booking**: Book tickets for flights, perform check-ins, and retrieve booking details.
- **User Management**: Admin-only features for managing user accounts.
- **Listings and Reviews**: Explore flight listings and add user-submitted reviews and ratings.
- **Queue Management**: Integration with RabbitMQ for messaging between services.
- **Swagger Integration**: Auto-generated API documentation for quick exploration and testing.
- **Validation**: Comprehensive request validation with detailed error messages.

---

## 🛠️ Tech Stack

### Backend
- **Java 17**
- **Spring Boot 3.3.4**
  - Spring Security
  - Spring Data JPA
  - Spring Validation
- **PostgreSQL**: Relational database for data management.
- **RabbitMQ**: Messaging system for asynchronous communication between services.
- **Lombok**: Simplifies boilerplate code with annotations.
- **Swagger**: Automatically generates interactive API documentation.

---

## 📊 System Architecture

This application follows a modular **microservice architecture**:

1. **Airline API**:
   - Manages core features like flights, tickets, and bookings.
   - Role-based access control for administrators and users.

2. **Messaging Service**:
   - Handles communication between services via RabbitMQ.
   - Supports queue-based messaging for real-time notifications.

3. **Payment Service**:
   - Processes payments and integrates with notification services.
   - Payment confirmation triggers user notifications.

4. **Notification Service**:
   - Listens to RabbitMQ queues for messages.
   - Sends notifications after payment processing.

5. **API Gateway**:
   - Acts as a single entry point for all services.
   - Routes requests to appropriate microservices.

---

## 📋 Project Modules

### Airline API
- **Port**: `8087`
- **Endpoints**:
  - `/api/flights`: Manage flight schedules and details.
  - `/api/bookings`: Handle bookings and ticket check-ins.
  - `/api/users`: Admin-only access for user management.

### Messaging Service
- **Port**: `8088`
- **Features**:
  - Manages generic messaging queues like `messageQueue`.

### Payment Service
- **Port**: `8089`
- **Features**:
  - Processes payment details.
  - Triggers RabbitMQ notification queues.

### Notification Service
- **Port**: `8090`
- **Features**:
  - Sends notifications for processed payments.

### API Gateway
- **Port**: `8080`
- **Features**:
  - Routes all requests to respective services.
  - Centralized API access via `/api/{service}` routes.

---

## 🛡️ Security

1. **JWT-based Authentication**:
   - Login endpoints provide JWT tokens.
   - Protected routes require a valid token in the `Authorization` header as `Bearer`.

2. **Role-based Access**:
   - ADMIN: Access to all endpoints.
   - USER: Restricted access to user-specific endpoints.

---


## 📊 ER Diagram

The ER diagram for the database schema can be found [here](https://github.com/Biromedic/AirlineAPI/blob/main/AirlineAPIER.png).

---

---

### Access the API documentation:

-	Swagger UI: https://airlineapi.onrender.com/swagger-ui/index.html

---

## 🛡️ Security

-	JWT-based Authentication: Login returns a JWT token that must be included in the Authorization header as Bearer <token> for protected routes.
    
-	Role-based Access: ADMIN and USER roles control access to endpoints.


## 📌 Testing

- Use Postman or any REST client for API testing.
  - Sample Endpoints:
      - POST /api/auth/signup: Register a user.
      - POST /api/auth/signin: Log in and receive JWT.
      - POST /api/flights/admin/create: Add a new flight (Admin-only).
      - POST /api/bookings/create: Book a ticket.
      - POST /api/payments/process: Process payment and trigger notifications.