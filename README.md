🎓 Smart Campus Portal

📝 Description

Smart Campus Portal is a Spring Boot-based application designed to streamline and enhance campus operations, providing students, lecturers, and administrators with a centralized platform to manage campus activities, events, and resources efficiently.

🚀 Features

Student and Staff Management

Event Calendar and Announcements/Notifications

Role-Based Access Control (Admin, Lecturer, Student)

RESTful APIs for web integration

Secure Authentication and Authorization

Responsive and Scalable Architecture

API Documentation with Swagger

🛠️ Technologies Used

Java 17+

Spring Boot 3.x

Spring Data JPA

Spring Security (JWT or Session-Based)

MySQL

Maven

Swagger/OpenAPI for API Documentation

⚙️ Setup and Installation

Prerequisites

Java 17 or higher

Maven

MySQL


Clone the Repository

git clone https://github.com/Smart-Campus-Portal/scp-backend.git

cd scp-backend

Configure the Database

Update src/main/resources/application.properties:

spring.datasource.url=jdbc:mysql://localhost:3305/scp_db

spring.datasource.username=your_db_username

spring.datasource.password=your_db_password

spring.jpa.hibernate.ddl-auto=update

spring.jpa.show-sql=true

Build the Application

./mvnw clean install

Run the Application

./mvnw spring-boot:run

Access the Portal

Base URL: http://localhost:8080/

Swagger API Docs: http://localhost:8080/swagger-ui.html

📖 API Documentation

The project integrates Swagger to make testing and exploring APIs easy:

Once the application is running, visit:

http://localhost:8080/swagger-ui.html
