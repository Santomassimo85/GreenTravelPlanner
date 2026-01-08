# Green Travel Planner (Backend)

A Spring Boot backend for a sustainable travel planning platform.
The system allows users to register, plan trips, manage destinations, and leave reviews, with a strong focus on security and integration with external services.

## Main Features

* Authentication & Security
    * Login/Register system using JWT (JSON Web Tokens).
    * Role management (TRAVELER vs ADMIN).
    * Passwords encrypted via BCrypt.
* Trip Management
    * Complete CRUD operations for trips.
    * External API Integration: Automatically retrieves the destination's Capital city using the RestCountries API when a trip is created.
    * Automatic status calculation (Scheduled / Ongoing / Completed) based on dates.
* User Management
    * Cloudinary Integration: Profile picture upload and management.
    * Cascading deletion system: Deleting a user automatically removes all their associated data (trips, reviews) to maintain database integrity.
* Error Handling
    * Structured and meaningful JSON responses for errors (e.g., 404 Not Found, 400 Bad Request), ensuring the client always receives clear feedback.

## Technology Stack

* Java 21 & Spring Boot 4.0.1
* PostgreSQL (Database)
* Spring Data JPA / Hibernate (ORM)
* Spring Security (Authentication/Authorization)
* Lombok (Boilerplate reduction)
* Cloudinary (Image Hosting)

## Configuration and Setup

### 1. Requirements
Ensure you have the following installed:
* Java JDK 21 or higher
* PostgreSQL
* Maven

### 2. Environment Variables (env.properties)
This project uses an env.properties file in the root directory to keep sensitive data secure.
Create a file named `env.properties` in the root directory of the project and add the following configurations:

# DATABASE CONFIGURATION
PG_DB_URL=jdbc:postgresql://localhost:5432/your_database_name
PG_USERNAME=postgres
PG_PASSWORD=your_postgres_password

# JWT CONFIGURATION (Security)
JWT_SECRET=insert_a_very_long_and_random_secret_string_here
JWT_EXPIRATION=86400000

# CLOUDINARY CONFIGURATION (Images)
CLOUDINARY_NAME=your_cloud_name
CLOUDINARY_API_KEY=your_api_key
CLOUDINARY_SECRET=your_api_secret

### 3. Running the Application
You can start the application using the terminal from the project root:

./mvnw spring-boot:run

Alternatively, run the GreenTravelPlannerApplication class directly from your IDE (IntelliJ).

## Testing (Postman)
A Postman collection file named GreenTravelPlanner.postman_collection.json is included in the main project folder.
Import this file into Postman to test all available endpoints, including Authentication, File Uploads, Trip management, and Error handling scenarios.

---
Backend Project - Epicode Final Project - Green Travel Planner 

Developed by: [Santomassimo Luca]