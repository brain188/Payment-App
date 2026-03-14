# Student Payment Portal

A web application for managing student fee payments. This project features a robust Spring Boot backend and a sleek Angular frontend, designed with a focus on user experience, secure processing, and real-time balance tracking.

## Features

- **Landing Page**: A modern, responsive design with clear call-to-actions and FAQ.
- **Secure Payment Processing**: Integrated validation and error handling for one-time fee payments.
- **Incentive System**: Automatically applies incentives based on payment amounts to encourage early or large payments.
- **Real-time Receipt**: Instant generation of a stylish receipt showing previous balance, payment, incentives, and new balance.
- **XAF Currency Support**: Fully localized for XAF with proper formatting and layout.
- **Negative Balance Handling**: Visual indicators (red highlighting) for outstanding balances.
- **Smart Feedback**: Immediate validation messages and auto-scrolling to errors.

## Technology Stack

### Backend

- **Java 21 / Spring Boot 3.2.3**
- **Spring Web**: RESTful API endpoints.
- **Spring Validation**: Robust request payload validation.
- **SLF4J/Logback**: Comprehensive logging.
- **BigDecimal**: High-precision monetary calculations.

### Frontend

- **Angular 17**
- **Standalone Components**: Modular and modern structure.
- **Reactive Forms**: Sophisticated form handling and validation.
- **Vanilla CSS**: Custom premium styling with modern variables and animations.
- **Remix Icon**: High-quality iconography.
- **Google Fonts (Inter)**: Modern typography.

## Architecture Overview

The system follows a classic client-server architecture:

1.  **Frontend (Angular)**: Handles the UI state, user interactions, and local validation. It communicates with the backend via REST API.
2.  **Backend (Spring Boot)**: Provides business logic, processes payments, and manages mock data persistence.
3.  **Data Layer**: Currently utilizes an in-memory repository for demonstration purposes, ensuring fast responses and easy setup.

## Getting Started

### Prerequisites

- JDK 17 or higher
- Node.js 18 or higher
- Angular CLI

### Backend Setup

1. Navigate to the `backend/payment` directory.
2. Run `mvnw run build` then `mvnw spring-boot:run` (or use your IDE).
3. The API will be available at `http://localhost:8080`.

### Frontend Setup

1. Navigate to the `frontend/payment-ui` directory.
2. Run `npm install` to install dependencies.
3. Run `ng serve` to launch the development server.
4. Access the portal at `http://localhost:4200`.

## Assumptions

### Student Data

- Student accounts and their balances are assumed to exist already, there is no account creation flow in scope. The balance is either mocked or seeded in memory for testing purposes.
- The student number is assumed to be a valid, pre-existing identifier. No lookup against an external system is performed.

### Payment Logic

- A payment amount is always in the same currency (no multi-currency support).
- The system date is used as the payment date when none is provided. The assumption is that the server timezone is correct and consistent.
- A student can make multiple one-time payments; there is no restriction on frequency.

### Balance

- The previous balance is always >= 0. No logic is implemented for overpayment (i.e., payment exceeding the current balance)

### Incentive

- The incentive/match is always applied automatically. There is no opt-out mechanism.
- Tier boundaries are based on the raw payment amount, not the total reduction.

## Trade-offs

### In-memory vs Real Database

A mock/in-memory repository is used instead of a real database to keep the setup simple and focused on the business logic. In production, this would be replaced with JPA and a real DB.

### No Authentication

The endpoint has no authentication or authorization. In a real system, only authorized users (students or admins) would be able to trigger payments.

### BigDecimal Precision

BigDecimal with HALF_UP rounding is used over double for accuracy, at the cost of slightly more verbose code. This is the correct trade-off for any financial application.

### No Idempotency

There is no idempotency key on the payment request, meaning duplicate requests could be processed twice. This can be acceptable for an exercise but would be critical to address in production.

### Minimal UI

The Angular frontend is kept simple and functional rather than polished, prioritizing correctness of the data flow over UX design.

---
