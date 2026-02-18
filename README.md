# RESTful API Assignment - Product Management System

## Assignment Information
- **Course:** Web Technology and Internet Course
- **Assignment:** Assignment 3
- **Student Name:** Jospin Nabonyimana
- **Student ID:** 26511
- **Date:** February 18th, 2026

---

## Project Overview

I have developed a RESTful API for a Product Management System using Spring Boot. This microservice application provides complete CRUD (Create, Read, Update, Delete) operations for managing product inventory in an e-commerce system.

---

## Technologies Used

- **Java:** JDK 21
- **Framework:** Spring Boot 4.0.2
- **Database:** PostgreSQL 18.1
- **ORM:** Hibernate (JPA)
- **Build Tool:** Maven
- **Server:** Apache Tomcat (Embedded)

---

## Project Structure

```
restfullApiAssignment3/
├── src/
│   ├── main/
│   │   ├── java/
│   │   │   └── auca/ac/rw/restfullApiAssignment/
│   │   │       ├── controller/
│   │   │       │   └── ProductController.java
│   │   │       ├── service/
│   │   │       │   └── ProductService.java
│   │   │       ├── repository/
│   │   │       │   └── ProductRepository.java
│   │   │       ├── modal/
│   │   │       │   └── Product.java
│   │   │       └── RestfullApiAssignmentApplication.java
│   │   └── resources/
│   │       └── application.properties
│   └── test/
├── pom.xml
└── README.md
```

---

## Database Configuration

I have configured PostgreSQL database with the following settings:

- **Database Name:** ecommerce_db
- **Host:** localhost
- **Port:** 5432
- **Username:** postgres
- **Hibernate DDL:** Auto-update enabled
- **SQL Logging:** Enabled for debugging

---

## API Endpoints

I have implemented the following RESTful endpoints:

### 1. Create Product (POST)
- **URL:** `http://localhost:8080/api/products/addProduct`
- **Method:** POST
- **Content-Type:** application/json
- **Request Body:**
```json
{
    "id": 2025,
    "name": "Smartphone",
    "description": "Samsung Galaxy S24",
    "price": 999.00,
    "category": "Electronics",
    "stockQuantity": 15
}
```
- **Response:** `"Product saved successfully."`

### 2. Read All Products (GET)
- **URL:** `http://localhost:8080/api/products/getAllProducts`
- **Method:** GET
- **Response:** JSON array of all products

### 3. Update Product (PUT)
- **URL:** `http://localhost:8080/api/products/updateProduct`
- **Method:** PUT
- **Content-Type:** application/json
- **Request Body:** Complete product object with updated fields
- **Response:** `"Product updated successfully."`

### 4. Partial Update - Stock Only (PATCH)
- **URL:** `http://localhost:8080/api/products/updateProductStock/{id}/{stockQuantity}`
- **Method:** PATCH
- **Example:** `http://localhost:8080/api/products/updateProductStock/2025/100`
- **Response:** `"Product stock updated successfully."`

### 5. Delete Product (DELETE)
- **URL:** `http://localhost:8080/api/products/deleteProduct/{id}`
- **Method:** DELETE
- **Example:** `http://localhost:8080/api/products/deleteProduct/2025`
- **Response:** `"Product deleted successfully."`

---

## Product Entity

I have designed the Product entity with the following attributes:

| Field | Type | Description |
|-------|------|-------------|
| id | Long | Primary Key (manually assigned) |
| name | String | Product name |
| description | String | Product description |
| price | double | Product price |
| category | String | Product category |
| stockQuantity | int | Available stock quantity |

---

## Features Implemented

✅ **Complete CRUD Operations**
- Create new products
- Retrieve all products
- Update entire product details
- Partial update (stock quantity only)
- Delete products

✅ **Database Integration**
- PostgreSQL database connectivity
- Hibernate ORM for data persistence
- Automatic table creation/update

✅ **RESTful Architecture**
- Proper HTTP methods (POST, GET, PUT, PATCH, DELETE)
- Appropriate HTTP status codes (200, 201, 404, 409, 500)
- JSON request/response format

✅ **Error Handling**
- Duplicate product validation
- Product not found handling
- Proper error messages

✅ **Layered Architecture**
- Controller layer for HTTP handling
- Service layer for business logic
- Repository layer for data access
- Entity/Model layer for data representation

---

## How to Run the Application

1. **Prerequisites:**
   - Install JDK 21
   - Install PostgreSQL
   - Install Maven

2. **Database Setup:**
   - Create database: `ecommerce_db`
   - Update credentials in `application.properties` if needed

3. **Build the Project:**
   ```bash
   mvn clean install
   ```

4. **Run the Application:**
   ```bash
   mvn spring-boot:run
   ```

5. **Application will start on:** `http://localhost:8080`

---

## Testing

I have thoroughly tested all CRUD operations using:
- **Postman** for API endpoint testing
- **pgAdmin 4** for database verification

For detailed screenshots of all tests, please refer to the **screenshots** folder in this repository.

---

## Verification

All CRUD operations have been verified:
- ✅ Products can be created successfully
- ✅ All products can be retrieved
- ✅ Products can be updated (full and partial)
- ✅ Products can be deleted
- ✅ Database reflects all changes correctly
- ✅ Proper error handling for edge cases

---

## Dependencies

Key dependencies used in this project:

```xml
<dependencies>
    <!-- Spring Boot Web -->
    <dependency>
        <groupId>org.springframework.boot</groupId>
        <artifactId>spring-boot-starter-web</artifactId>
        <version>4.0.1</version>
    </dependency>
    
    <!-- Spring Boot Data JPA -->
    <dependency>
        <groupId>org.springframework.boot</groupId>
        <artifactId>spring-boot-starter-data-jpa</artifactId>
        <version>4.0.1</version>
    </dependency>
    
    <!-- PostgreSQL Driver -->
    <dependency>
        <groupId>org.postgresql</groupId>
        <artifactId>postgresql</artifactId>
        <version>42.7.8</version>
    </dependency>
</dependencies>
```

---

## Conclusion

I have successfully implemented a fully functional RESTful API for product management with complete CRUD operations, proper database integration using Hibernate, and a well-structured layered architecture following Spring Boot best practices.

---

**Submitted by:** Jospin Nabonyimana (26511)
