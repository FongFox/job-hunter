# Job Hunter - RESTful API with Spring Boot

**Job Hunter** is a RESTful API project built with **Spring Boot** to connect job seekers and employers in the job searching and hiring process. It offers functionalities such as profile creation, job searching, notification management, and communication between candidates and recruiters.

## 🚀 Project Goals
1. Build a robust and flexible backend RESTful API.
2. Bridge the gap between employers and job seekers through APIs.
3. Provide job search capabilities based on criteria like location, skills, and salary.
4. Offer user profile management functionality.

## 🛠️ Technologies Used
- **Spring Boot**: The main framework for building the application.
- **Spring Data JPA**: For interacting with the database.
- **H2 Database**: In-memory database (can be replaced with MySQL/PostgreSQL if needed).
- **Spring Security**: For authentication and authorization.
- **JWT (JSON Web Token)**: For managing user sessions.
- **Gradle**: Build tool.
- **Swagger UI**: For API documentation.

## 📂 Project Structure
```
job-hunter/
│
├── src/main/java/com/example/jobhunter/
│   ├── controller/    # REST API controllers
│   ├── service/       # Business logic processing
│   ├── repository/    # Database interaction
│   ├── model/         # Entity definitions
│   ├── config/        # Security, JWT, Swagger configurations
│   └── dto/           # Data Transfer Objects
│
├── src/main/resources/
│   ├── application.properties  # Application configuration
│   └── data.sql                # Sample data
│
└── build.gradle.kts            # Gradle build configuration
```

## 🌟 Key Features
### 1. **For Job Seekers**
- Create and update personal profiles.
- Search for jobs based on criteria like location, skills, and salary.
- Apply for desired job postings.
- Receive notifications about suitable jobs.

### 2. **For Employers**
- Post job openings.
- Manage applications from job seekers.
- Send notifications to candidates.

### 3. **System API**
- **Authentication**:
  - User login and registration.
  - JWT issuance and refresh.
- **User Management**:
  - Add, update, delete user information.
- **Job Management**:
  - Add, update, delete job postings.
  - Search for jobs.
- **Notifications**:
  - Send and receive system notifications.

## 📋 Installation Guide
### Prerequisites:
- **Java 17** or higher.
- **Gradle** (already configured in the project).

### Steps to Run:
1. Clone the repository:
   ```bash
   git clone <repository-url>
   cd job-hunter
   ```

2. Start the application:
   ```bash
   ./gradlew bootRun
   ```

3. Access Swagger UI to test the API:
   - **Swagger URL**: [http://localhost:8080/swagger-ui.html](http://localhost:8080/swagger-ui.html)

4. Database configuration (optional):
   - Open the `application.properties` file and update the database connection details as needed.

## 🔥 API Documentation
The project uses Swagger for automatic API documentation. Once the application is running, you can access the documentation at:
- [http://localhost:8080/swagger-ui.html](http://localhost:8080/swagger-ui.html)

## 🌐 Contribution
We welcome contributions from the community! If you'd like to contribute, please follow these steps:
1. Fork the project.
2. Create a new branch:
   ```bash
   git checkout -b feature/new-feature
   ```
3. Commit your changes:
   ```bash
   git commit -m "Add new feature"
   ```
4. Submit a pull request.

## 📧 Contact
For any questions or feedback, feel free to contact us:
- **Email**: phong.tgn.coder@example.com
- **GitHub**: [job-hunter](https://github.com/FongFox/job-hunter)