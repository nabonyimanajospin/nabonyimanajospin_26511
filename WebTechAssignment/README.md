# Spring Boot RESTful API Assignment
## Web Technology - Module 1-3

**Student Name:** Nabonyimana Jospin  
**Student ID:** 26511  
**Branch:** restFull_api_26511  
**Submission Date:** February 11, 2026

---

## 📋 Assignment Overview

This repository contains **6 Spring Boot RESTful API projects** (5 required + 1 bonus) demonstrating REST controller implementation with proper HTTP methods, status codes, and CRUD operations.

---

## 🛠️ Technologies Used

- **Framework:** Spring Boot 4.0.2
- **Language:** Java 21
- **Build Tool:** Maven
- **Dependencies:** Spring Web, Spring Boot Starter Test
- **Testing:** Postman, Web Browser

---

## 📁 Project Structure
```
WebTechAssignment/
├── question1_librarymanagementapi/      (Port 8080 - 5 endpoints)
├── question2_student_registration_api/   (Port 8081 - 6 endpoints)
├── question3_restaurant_menu_api/        (Port 8082 - 8 endpoints)
├── question4_ecommerce_product_api/      (Port 8083 - 12 endpoints)
├── question5_task_management_api/        (Port 8084 - 8 endpoints)
└── bonus_user_profile_api/               (Port 8085 - 10 endpoints)
```

---

## 🚀 How to Run Any Project
```bash
# Navigate to specific question folder
cd question1_librarymanagementapi

# Run the application
mvn spring-boot:run

# Access at the assigned port
# Question 1: http://localhost:8080
# Question 2: http://localhost:8081
# Question 3: http://localhost:8082
# Question 4: http://localhost:8083
# Question 5: http://localhost:8084
# Bonus:      http://localhost:8085
```

---

## 📚 Question 1: Library Book Management API (20 Points)

**Port:** 8080  
**Package Structure:** `controller/BookController.java`, `model/Book.java`

### Book Model
- id (Long)
- title (String)
- author (String)
- isbn (String)
- publicationYear (int)

### Endpoints (5 Total)

| Method | Endpoint | Description | Status |
|--------|----------|-------------|--------|
| GET | `/api/books` | Get all books | 200 |
| GET | `/api/books/{id}` | Get book by ID | 200/404 |
| GET | `/api/books/search?title={title}` | Search by title | 200 |
| POST | `/api/books` | Add new book | 201 |
| DELETE | `/api/books/{id}` | Delete book | 204/404 |

### Sample Data
3 books: Clean Code, Effective Java, Spring in Action

### Test URLs
```
http://localhost:8080/api/books
http://localhost:8080/api/books/1
http://localhost:8080/api/books/search?title=Clean
```

---

## 🎓 Question 2: Student Registration API (20 Points)

**Port:** 8081  
**Package Structure:** `controller/StudentController.java`, `model/Student.java`

### Student Model
- studentId (Long)
- firstName (String)
- lastName (String)
- email (String)
- major (String)
- gpa (Double)

### Endpoints (6 Total)

| Method | Endpoint | Description | Status |
|--------|----------|-------------|--------|
| GET | `/api/students` | Get all students | 200 |
| GET | `/api/students/{studentId}` | Get by ID | 200/404 |
| GET | `/api/students/major/{major}` | Get by major | 200 |
| GET | `/api/students/filter?gpa={minGpa}` | Filter by GPA | 200 |
| POST | `/api/students` | Register student | 201 |
| PUT | `/api/students/{studentId}` | Update student | 200/404 |

### Sample Data
5 students with different majors (Computer Science, Mathematics, Engineering) and GPAs

### Test URLs
```
http://localhost:8081/api/students
http://localhost:8081/api/students/major/Computer%20Science
http://localhost:8081/api/students/filter?gpa=3.5
```

---

## 🍽️ Question 3: Restaurant Menu API (20 Points)

**Port:** 8082  
**Package Structure:** `controller/MenuController.java`, `model/MenuItem.java`

### MenuItem Model
- id (Long)
- name (String)
- description (String)
- price (Double)
- category (String) - Appetizer, Main Course, Dessert, Beverage
- available (boolean)

### Endpoints (8 Total)

| Method | Endpoint | Description | Status |
|--------|----------|-------------|--------|
| GET | `/api/menu` | Get all items | 200 |
| GET | `/api/menu/{id}` | Get by ID | 200/404 |
| GET | `/api/menu/category/{category}` | Get by category | 200 |
| GET | `/api/menu/available?available=true` | Get available | 200 |
| GET | `/api/menu/search?name={name}` | Search by name | 200 |
| POST | `/api/menu` | Add item | 201 |
| PUT | `/api/menu/{id}/availability` | Toggle availability | 200/404 |
| DELETE | `/api/menu/{id}` | Remove item | 204/404 |

### Sample Data
8+ menu items across all categories

### Test URLs
```
http://localhost:8082/api/menu
http://localhost:8082/api/menu/category/Main%20Course
http://localhost:8082/api/menu/available?available=true
```

---

## 🛒 Question 4: E-Commerce Product API (25 Points)

**Port:** 8083  
**Package Structure:** `controller/ProductController.java`, `model/Product.java`

### Product Model
- productId (Long)
- name (String)
- description (String)
- price (Double)
- category (String)
- stockQuantity (int)
- brand (String)

### Endpoints (12 Total)

| Method | Endpoint | Description | Status |
|--------|----------|-------------|--------|
| GET | `/api/products` | Get all products | 200 |
| GET | `/api/products?page={p}&limit={l}` | Paginated | 200 |
| GET | `/api/products/{productId}` | Get by ID | 200/404 |
| GET | `/api/products/category/{category}` | Get by category | 200 |
| GET | `/api/products/brand/{brand}` | Get by brand | 200 |
| GET | `/api/products/search?keyword={k}` | Search | 200 |
| GET | `/api/products/price-range?min={min}&max={max}` | Price filter | 200 |
| GET | `/api/products/in-stock` | In stock only | 200 |
| POST | `/api/products` | Add product | 201 |
| PUT | `/api/products/{productId}` | Update product | 200/404 |
| PATCH | `/api/products/{productId}/stock?quantity={q}` | Update stock | 200/404 |
| DELETE | `/api/products/{productId}` | Delete | 204/404 |

### Sample Data
10+ products with different categories, brands, prices, and stock levels

### Test URLs
```
http://localhost:8083/api/products
http://localhost:8083/api/products?page=1&limit=5
http://localhost:8083/api/products/price-range?min=100&max=500
http://localhost:8083/api/products/in-stock
```

---

## ✅ Question 5: Task Management API (15 Points)

**Port:** 8084  
**Package Structure:** `controller/TaskController.java`, `model/Task.java`

### Task Model
- taskId (Long)
- title (String)
- description (String)
- completed (boolean)
- priority (String) - LOW, MEDIUM, HIGH
- dueDate (String) - YYYY-MM-DD

### Endpoints (8 Total)

| Method | Endpoint | Description | Status |
|--------|----------|-------------|--------|
| GET | `/api/tasks` | Get all tasks | 200 |
| GET | `/api/tasks/{taskId}` | Get by ID | 200/404 |
| GET | `/api/tasks/status?completed={bool}` | Filter by status | 200 |
| GET | `/api/tasks/priority/{priority}` | Get by priority | 200 |
| POST | `/api/tasks` | Create task | 201 |
| PUT | `/api/tasks/{taskId}` | Update task | 200/404 |
| PATCH | `/api/tasks/{taskId}/complete` | Mark complete | 200/404 |
| DELETE | `/api/tasks/{taskId}` | Delete task | 204/404 |

### Sample Data
Sample tasks with different priorities and completion statuses

### Test URLs
```
http://localhost:8084/api/tasks
http://localhost:8084/api/tasks/status?completed=false
http://localhost:8084/api/tasks/priority/HIGH
```

---

## 🎁 BONUS: User Profile API (Extra 20 Points)

**Port:** 8085  
**Package Structure:** `controller/UserProfileController.java`, `model/UserProfile.java`

### UserProfile Model
- userId (Long)
- username (String)
- email (String)
- fullName (String)
- age (int)
- country (String)
- bio (String)
- active (boolean)

### Endpoints (10 Total)

| Method | Endpoint | Description | Status |
|--------|----------|-------------|--------|
| GET | `/api/users` | Get all users | 200 |
| GET | `/api/users/{userId}` | Get by ID | 200/404 |
| GET | `/api/users/search?username={u}` | Search username | 200 |
| GET | `/api/users/country/{country}` | Get by country | 200 |
| GET | `/api/users/age-range?min={min}&max={max}` | Age filter | 200 |
| POST | `/api/users` | Create profile | 201 |
| PUT | `/api/users/{userId}` | Update profile | 200/404 |
| PATCH | `/api/users/{userId}/activate` | Activate | 200/404 |
| PATCH | `/api/users/{userId}/deactivate` | Deactivate | 200/404 |
| DELETE | `/api/users/{userId}` | Delete | 204/404 |

### Response Wrapper
```json
{
  "success": true,
  "message": "User profile created successfully",
  "data": { ... }
}
```

### Test URLs
```
http://localhost:8085/api/users
http://localhost:8085/api/users/search?username=john
http://localhost:8085/api/users/country/Rwanda
```

---

## 🧪 Testing Evidence

All endpoints tested using:
- **Browser** - GET requests
- **Postman** - POST, PUT, PATCH, DELETE requests

Screenshots available in each project's `screenshots/` folder showing:
- Request URLs
- HTTP methods
- Request bodies (for POST/PUT)
- Response data
- Status codes

---

## ✅ Submission Checklist

- [x] **6 separate Spring Boot projects** (5 required + 1 bonus)
- [x] **Proper package structure** 
- [x] **All endpoints implemented** with correct HTTP methods
- [x] **Sample data initialized** in constructors
- [x] **All endpoints tested** and working
- [x] **README.md documentation** complete
- [x] **Screenshots captured** for all endpoints
- [x] **Code committed** to Git
- [x] **Pushed to branch** `restFull_api_26511`

---

## 📊 Summary Statistics

| Question | Points | Endpoints | Port | Status |
|----------|--------|-----------|------|--------|
| Question 1 | 20 | 5 | 8080 | ✅ Complete |
| Question 2 | 20 | 6 | 8081 | ✅ Complete |
| Question 3 | 20 | 8 | 8082 | ✅ Complete |
| Question 4 | 25 | 12 | 8083 | ✅ Complete |
| Question 5 | 15 | 8 | 8084 | ✅ Complete |
| Bonus | 20 | 10 | 8085 | ✅ Complete |
| **TOTAL** | **120** | **49** | - | **✅ Complete** |

---

## 🎯 Key Features Demonstrated

- ✅ REST API design and implementation
- ✅ Spring Boot framework usage
- ✅ HTTP methods (GET, POST, PUT, PATCH, DELETE)
- ✅ Status codes (200, 201, 204, 404)
- ✅ Path variables (`@PathVariable`)
- ✅ Query parameters (`@RequestParam`)
- ✅ Request body (`@RequestBody`)
- ✅ Controller annotations (`@RestController`, `@RequestMapping`)
- ✅ CRUD operations
- ✅ In-memory data storage
- ✅ Search and filter functionality
- ✅ Pagination implementation

---

## 🖼️ Screenshots

All endpoint testing screenshots are available in the `/screenshots` folder:

| Question | Screenshots Location |
|----------|---------------------|
| Question 1 - Library API | `screenshots/question1/` |
| Question 2 - Student API | `screenshots/question2/` |
| Question 3 - Restaurant API | `screenshots/question3/` |
| Question 4 - E-Commerce API | `screenshots/question4/` |
| Question 5 - Task API | `screenshots/question5/` |
| Bonus - User Profile API | `screenshots/bonus/` |

Each screenshot is named to show the endpoint and HTTP method tested:
- `get-all-books.png`
- `post-add-student.png`
- `put-update-product.png`
- etc.
---

## 👨‍💻 Author

**Nabonyimana Jospin**  
Student ID: 26511  
Web Technology Course  
Spring Boot RESTful API Assignment 

---

## 📝 Notes

This assignment demonstrates comprehensive understanding of Spring Boot REST API development, including proper endpoint design, HTTP protocol usage, data modeling, and API testing methodologies.

---

**End of Documentation**