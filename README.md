# Employee Management System

A comprehensive REST API for managing employee data built with Spring Boot. This application provides full CRUD (Create, Read, Update, Delete) operations for employee management with a clean, well-documented API interface.

## Features

- **Complete CRUD Operations**: Create, read, update, and delete employees
- **Department Filtering**: Retrieve employees by department
- **Data Validation**: Input validation with meaningful error messages
- **API Documentation**: Interactive Swagger UI for easy API exploration
- **Clean Architecture**: Separation of concerns with DTOs, services, and repositories
- **Exception Handling**: Proper error handling with custom exceptions
- **Email Uniqueness**: Ensures no duplicate email addresses in the system

## Tech Stack

### Backend Framework
- **Spring Boot 3.x** - Main application framework
- **Spring Web** - REST API development
- **Spring Data JPA** - Data persistence and repository layer
- **Spring Boot Starter Validation** - Input validation

### Database
- **H2 Database** (in-memory) - For development and testing
- **JPA/Hibernate** - Object-relational mapping

### Documentation
- **SpringDoc OpenAPI 3** - API documentation
- **Swagger UI** - Interactive API documentation interface

### Build Tool
- **Maven** - Dependency management and build automation

### Java Version
- **Java 17+** - Programming language

## Prerequisites

Before running this application, ensure you have:

- **Java 17 or higher** installed
- **Maven 3.6+** installed
- **Git** for version control

## Getting Started

### Clone the Repository
```bash
git clone https://github.com/yourusername/employee-management-system.git
cd employee-management-system
```

### Run the Application
```bash
# Using Maven wrapper (recommended)
./mvnw spring-boot:run

# Or using Maven directly
mvn spring-boot:run
```

### Access the Application
- **Application Base URL**: `http://localhost:8080`
- **Swagger UI**: `http://localhost:8080/swagger-ui.html`
- **API Documentation**: `http://localhost:8080/api-docs`

## API Endpoints

### Employee Operations

| Method | Endpoint | Description |
|--------|----------|-------------|
| `GET` | `/api/employees` | Get all employees |
| `GET` | `/api/employees/{id}` | Get employee by ID |
| `GET` | `/api/employees/department/{department}` | Get employees by department |
| `POST` | `/api/employees` | Create new employee |
| `PUT` | `/api/employees/{id}` | Update employee |
| `DELETE` | `/api/employees/{id}` | Delete employee |

### Sample Request Body (POST/PUT)
```json
{
  "firstName": "John",
  "lastName": "Doe",
  "email": "john.doe@company.com",
  "department": "Engineering",
  "position": "Software Engineer",
  "salary": 75000.00
}
```

### Sample Response Body
```json
{
  "id": 1,
  "firstName": "John",
  "lastName": "Doe",
  "fullName": "John Doe",
  "email": "john.doe@company.com",
  "department": "Engineering",
  "position": "Software Engineer",
  "salary": 75000.00
}
```

## Project Structure

```
src/main/java/com/example/employeemanagement/
├── config/
│   └── SwaggerConfig.java              # Swagger/OpenAPI configuration
├── controller/
│   └── EmployeeController.java         # REST API endpoints
├── dto/
│   ├── EmployeeRequestDTO.java         # Request data transfer object
│   ├── EmployeeResponseDTO.java        # Response data transfer object
│   └── EmployeeMapper.java             # Entity-DTO mapping utilities
├── exception/
│   └── ResourceNotFoundException.java  # Custom exception handling
├── model/
│   └── Employee.java                   # JPA entity
├── repository/
│   └── EmployeeRepository.java         # Data access layer
├── service/
│   └── EmployeeService.java            # Business logic layer
└── EmployeeManagementApplication.java  # Main application class
```

## Configuration

### Database Configuration
The application uses H2 in-memory database by default. Access the H2 console at:
- **URL**: `http://localhost:8080/h2-console`
- **JDBC URL**: `jdbc:h2:mem:testdb`
- **Username**: `sa`
- **Password**: (leave empty)

### Application Properties
```properties
# Server configuration
server.port=8080

# H2 Database configuration
spring.datasource.url=jdbc:h2:mem:testdb
spring.datasource.driver-class-name=org.h2.Driver
spring.datasource.username=sa
spring.datasource.password=

# JPA configuration
spring.jpa.database-platform=org.hibernate.dialect.H2Dialect
spring.jpa.hibernate.ddl-auto=create-drop
spring.jpa.show-sql=true

# H2 Console (for development)
spring.h2.console.enabled=true

# Swagger configuration
springdoc.api-docs.path=/api-docs
springdoc.swagger-ui.path=/swagger-ui.html
```

## Testing the API

### Using Swagger UI
1. Navigate to `http://localhost:8080/swagger-ui.html`
2. Explore the available endpoints
3. Click "Try it out" on any endpoint
4. Fill in the required parameters
5. Click "Execute" to test the API

### Using cURL Examples

**Create Employee:**
```bash
curl -X POST http://localhost:8080/api/employees \
  -H "Content-Type: application/json" \
  -d '{
    "firstName": "John",
    "lastName": "Doe",
    "email": "john.doe@company.com",
    "department": "Engineering",
    "position": "Software Engineer",
    "salary": 75000.00
  }'
```

**Get All Employees:**
```bash
curl -X GET http://localhost:8080/api/employees
```

**Get Employee by ID:**
```bash
curl -X GET http://localhost:8080/api/employees/1
```

## Future Enhancements

- Add authentication and authorization (Spring Security)
- Implement pagination for large datasets
- Add search functionality
- Integration with external databases (MySQL, PostgreSQL)
- Add unit and integration tests
- Docker containerization
- Add employee photo upload functionality
- Implement audit logging

## Contributing

1. Fork the repository
2. Create a feature branch (`git checkout -b feature/amazing-feature`)
3. Commit your changes (`git commit -m 'Add some amazing feature'`)
4. Push to the branch (`git push origin feature/amazing-feature`)
5. Open a Pull Request
