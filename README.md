# ChoreChamp: A Spring Boot API for Chore and Mission Management

[![Java](https://img.shields.io/badge/Java-ED8B00?style=for-the-badge&logo=java&logoColor=white)](https://www.java.com/)
[![Spring Boot](https://img.shields.io/badge/Spring_Boot-6DB33F?style=for-the-badge&logo=spring-boot&logoColor=white)](https://spring.io/projects/spring-boot)
[![PostgreSQL](https://img.shields.io/badge/PostgreSQL-316192?style=for-the-badge&logo=postgresql&logoColor=white)](https://www.postgresql.org/)
![Maven](https://img.shields.io/badge/Maven-C71A36?style=for-the-badge&logo=apache-maven&logoColor=white)
![PostgreSQL](https://img.shields.io/badge/PostgreSQL-4169E1?style=for-the-badge&logo=postgresql&logoColor=white)
![JPA](https://img.shields.io/badge/JPA-007396?style=for-the-badge&logo=java&logoColor=white)
![JUnit](https://img.shields.io/badge/JUnit-25A162?style=for-the-badge&logo=java&logoColor=white)
![REST API](https://img.shields.io/badge/REST_API-FF6F61?style=for-the-badge&logo=api&logoColor=white)
![JSON](https://img.shields.io/badge/JSON-000000?style=for-the-badge&logo=json&logoColor=white)
![JDBC](https://img.shields.io/badge/JDBC-007396?style=for-the-badge&logo=java&logoColor=white)
![Git](https://img.shields.io/badge/Git-F05032?style=for-the-badge&logo=git&logoColor=white)
[![ESLint](https://img.shields.io/badge/ESLint-4B32C3?style=for-the-badge&logo=eslint&logoColor=white)](https://eslint.org/)
[![Prettier](https://img.shields.io/badge/Prettier-F7B93E?style=for-the-badge&logo=prettier&logoColor=black)](https://prettier.io/)

Chore-app-backend is a comprehensive Spring Boot API designed to help users manage their chores, missions, and rewards efficiently. 
This application provides a robust backend for creating, updating, and tracking household tasks and personal goals.
The application is built with scalability and performance in mind, utilizing Spring Boot's powerful features and a PostgreSQL database for data persistence.

## Demo
```
link: <>
```

### Installation

Prerequisites:
- Java Development Kit (JDK) 17 or later
- Maven 3.6 or later
- PostgreSQL 12 or later

Steps:
1. Clone the repository:
   ```
   git clone <https://github.com/Msambere/chore-app-backend.git>
   ```
2. Navigate to the project repository
   ```
   cd chore-api-backend
   ```

3. Configure the database connection in `chore-app-backend/target/env.properties`:
   ```
   DATABASE_URL=jdbc:postgresql://<host>:<port>/<database>
   SPRING_DATASOURCE_USERNAME=<username>
   SPRING_DATASOURCE_PASSWORD=<password>
   SPRING_HIBERNATE_DDL_AUTO=create-drop OR update
   ```

### Getting Started

1. Build the project:
   ```
   Build - Build Project/Rebuild Project
   ```

2. Run the application:
   ```
   Play Button or ^R
   ```

Once the application is running, you can interact with the API using HTTP requests. 
Here are some example endpoints:

```
https://docs.google.com/document/d/1jNC8aHqeOFAqd8awg3LWDdijawY-Kyjb_RuqoFddtTw/edit?tab=t.0
```

## Data Flow

The ChoreChamp API follows a typical Spring Boot MVC architecture:

1. Client sends an HTTP request to a specific endpoint.
2. The appropriate Controller receives the request and validates the input.
3. The Controller delegates business logic to the corresponding Service.
4. The Service interacts with one or more Repositories to perform CRUD operations on the database.
5. The Service processes the data and returns it to the Controller.
6. The Controller formats the response and sends it back to the client.

```
Client <-> Controller <-> Service <-> Repository <-> Database
```

Note: The application uses Spring Data JPA for database interactions, which simplifies data access and management.

### 🚀 Contact Us
[![LinkedIn](https://img.shields.io/badge/LinkedIn-0077B5?style=for-the-badge&logo=linkedin&logoColor=white)](https://www.linkedin.com/in/amber-edwards-swe/)
[![GitHub](https://img.shields.io/badge/GitHub-181717?style=for-the-badge&logo=github&logoColor=white)](https://github.com/Msambere)
[![Email](https://img.shields.io/badge/Email-D14836?style=for-the-badge&logo=gmail&logoColor=white)](mailto:chorechamp.trio@gmail.com)