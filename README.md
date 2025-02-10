# ChoreChampAPI: A Spring Boot API for Chore and Mission Management
[![Java](https://img.shields.io/badge/Java-ED8B00?style=for-the-badge&logo=java&logoColor=white)](https://www.java.com/)
[![Spring Boot](https://img.shields.io/badge/Spring_Boot-6DB33F?style=for-the-badge&logo=spring-boot&logoColor=white)](https://spring.io/projects/spring-boot)
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

ChoreChamp is a full stack application designed to help users manage their chores, missions, and rewards efficiently. 
This Spring Boot API provides a robust backend for creating, updating, and tracking household tasks and personal goals.

## ER Diagram
![ER Diagram](./Capstone_ER_Diagram_V3.png)

### Key Files and Folders:
- `ada.chore_api_v2`: Holds the folders for each entity. Each entity folder contains its class, controller, service,
  repository and response body file.
- `pom.xml`: Maven project configuration file.
- `application.properties`: Configuration file for Spring Boot application settings.
- `ChoreApiV2Application.java`: The main entry point for the Spring Boot application.

### Installation

Prerequisites:
- Java Development Kit (JDK) 17 or later
- Maven 3.6 or later
- PostgreSQL 12 or later

Steps:
1. Clone the repository and open project in an IDE:
   ```
     git clone <https://github.com/Msambere/chore-app-backend.git>
   ```

2. Create a postgresSQL database for the api.
3. Create a `env.properties` file in the project root folder, add it to the `.gitignore`, and add the following content:
   ```
   DATABASE_URL=jdbc:postgresql://<host>:<port>/<database>
   SPRING_DATASOURCE_USERNAME=<username>
   SPRING_DATASOURCE_PASSWORD=<password>
   SPRING_HIBERNATE_DDL_AUTO=<update | create-drop>


### Getting Started
1. Build the project:
   ```
   ./mvnw clean install
   ```

2. Run the application:
   ```
   ./mvnw spring-boot:run
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

## 🚀 Contact Us
### Amber Edwards - Fullstack SWE
[![LinkedIn](https://img.shields.io/badge/LinkedIn-0077B5?style=for-the-badge&logo=linkedin&logoColor=white)](https://www.linkedin.com/in/amber-edwards-swe/)
[![GitHub](https://img.shields.io/badge/GitHub-181717?style=for-the-badge&logo=github&logoColor=white)](https://github.com/Msambere)
[![Email](https://img.shields.io/badge/Email-D14836?style=for-the-badge&logo=gmail&logoColor=white)](mailto:a.r.a.edwards@gmail.com)
### Anh Tran - Fullstack SWE
[![LinkedIn](https://img.shields.io/badge/LinkedIn-0077B5?style=for-the-badge&logo=linkedin&logoColor=white)](https://www.linkedin.com/in/anhtran077/)
[![GitHub](https://img.shields.io/badge/GitHub-181717?style=for-the-badge&logo=github&logoColor=white)](https://github.com/momofAnAl)
[![Email](https://img.shields.io/badge/Email-D14836?style=for-the-badge&logo=gmail&logoColor=white)](mailto:anhtr077@gmail.com)

### Salma Anany - Fullstack SWE
[![LinkedIn](https://img.shields.io/badge/LinkedIn-0077B5?style=for-the-badge&logo=linkedin&logoColor=white)](https://www.linkedin.com/in/salma-anany/)
[![GitHub](https://img.shields.io/badge/GitHub-181717?style=for-the-badge&logo=github&logoColor=white)](https://github.com/SalmaAnany)
[![Email](https://img.shields.io/badge/Email-D14836?style=for-the-badge&logo=gmail&logoColor=white)](mailto:salmayousry5@gmail.com)