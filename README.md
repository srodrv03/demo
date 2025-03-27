# Price Management API

## 📋 Project Overview

This is a Spring Boot microservice for managing and retrieving price information, designed using Domain-Driven Design (DDD) and Hexagonal Architecture principles.

## 🚀 Technologies

![Java](https://img.shields.io/badge/Java-21-red)
![Spring Boot](https://img.shields.io/badge/Spring%20Boot-3.4-green)
![Maven](https://img.shields.io/badge/Maven-3.8%2B-blue)
![H2 Database](https://img.shields.io/badge/H2-Database-orange)
![OpenAPI](https://img.shields.io/badge/OpenAPI-Swagger-brightgreen)

- **Java 21**
- **Spring Boot 3.4**
- **Maven**
- **H2 Database**
- **OpenAPI/Swagger**
- **JUnit 5**
- **Mockito**

## 🛠️ Prerequisites

- JDK 21
- Maven 3.8+

## 📂 Project Structure
```bash
─src/main/java/com/example/demo
├───application
│   ├───dto
│   └───usecase
├───domain
│   ├───model
│   └───repository
└───infraestructure
    ├───adapter
    │   ├───exception
    │   ├───input
    │   │   └───rest
    │   └───output
    │       └───h2
    └───config
```


## 🔧 Setup and Installation

1. Clone the repository
```bash
git clone https://github.com/srodrv03/demo.git
cd demo
```

2. Build the project
```bash
mvn clean install
```
3. Run the application
```bash
mvn spring-boot:run
```
## 🧪 Running Tests
```bash
mvn test
```

### ⚠️
In `PriceControllerTest` class, you will find the proposed test cases in the announcement under ‘Proposed test cases’.
## 📚 API Documentation

The API is documented using OpenAPI/Swagger:

- Swagger UI: http://localhost:8080/swagger-ui.html
- API Docs Endpoint: http://localhost:8080/v3/api-docs

## 🔍 API Endpoints

### Retrieve Price

Endpoint: `GET /api/prices/find`

#### **Parameters:**

- `productId `(Long, required): Product identifier
- `brandId `(Long, required): Brand identifier
- `applicationDate `(LocalDateTime, required): Date of price application

**Example Request:**

```bash
curl -X GET "http://localhost:8080/api/prices/find?productId=1&brandId=1&applicationDate=2020-06-14T10:00:00" -H "accept: application/json"
```

## 📋 TODO

1. [ ] Implement integration tests
2. [ ] Implement logging
3. [ ] Create Docker configuration

## 📞 Contact
### Sergio Rodriguez

- Email: sergio.1999@hotmail.com
- Project Link: https://github.com/srodrv03/demo
