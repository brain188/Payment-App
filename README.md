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
2. Run `./mvnw spring-boot:run` (or use your IDE).
3. The API will be available at `http://localhost:8080`.

### Frontend Setup

1. Navigate to the `frontend/payment-ui` directory.
2. Run `npm install` to install dependencies.
3. Run `npm start` to launch the development server.
4. Access the portal at `http://localhost:4200`.

## Design Decisions

- **XAF Currency**: The application uses XAF as its primary currency, with custom spacing and layout adjustments to accommodate the three-letter code.
- **Absolute Values in Receipt**: To maintain a clean look, all balances in the receipt are shown as absolute values, with red text indicating a negative (outstanding) balance.
- **Immediate Validation**: Form validation triggers on click to provide instant feedback, improving UX over standard "blur" or "input" event triggers.

---
