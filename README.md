<p align="center">
  <img src="https://capsule-render.vercel.app/api?type=waving&color=0:00C9FF,100:92FE9D&height=200&section=header&text=Social%20Media%20Backend%20API&fontSize=40&fontColor=ffffff&animation=fadeIn&fontAlignY=35"/>
</p>

<h1 align="center">🚀 Social Media Backend API</h1>

<p align="center">
  <b>Spring Boot • PostgreSQL • JPA • Hibernate • Validation</b>
</p>

<p align="center">
  <img src="https://img.shields.io/badge/Backend-SpringBoot-success?style=for-the-badge"/>
  <img src="https://img.shields.io/badge/Database-PostgreSQL-blue?style=for-the-badge"/>
  <img src="https://img.shields.io/badge/ORM-Hibernate-orange?style=for-the-badge"/>
  <img src="https://img.shields.io/badge/Architecture-Layered-brightgreen?style=for-the-badge"/>
</p>

---

# 📌 Project Overview

A production-ready Social Media Backend built using Spring Boot and PostgreSQL.

This project demonstrates:

✔ Clean REST API Design  
✔ Layered Architecture  
✔ ORM with Hibernate  
✔ Entity Relationships  
✔ Validation & Exception Handling  

Architecture Flow:

Client → Controller → Service → Repository → Database

---

# 🧠 Key Features

✅ Create & Manage Users  
✅ Create Posts linked to Users  
✅ Add Comments to Posts  
✅ Like / Unlike Posts  
✅ Validation using @Valid, @NotBlank, @Email  
✅ Proper HTTP Status Codes (201, 200, 400, 404, 204)  
✅ Global Exception Handling  

---

# 🗂️ Project Structure

```
src/main/java/com/example/demo
│
├── controller
│   ├── UserController
│   ├── PostController
│   ├── CommentController
│   └── LikeController
│
├── service
│   ├── UserService
│   ├── PostService
│   ├── CommentService
│   └── LikeService
│
├── repository
│   ├── UserRepo
│   ├── PostRepo
│   ├── CommentRepo
│   └── LikeRepo
│
├── model
│   ├── User
│   ├── Post
│   ├── Comment
│   └── Like
│
└── exception
    ├── ApiError
    ├── BadRequestException
    ├── NotFoundException
    └── GlobalExceptionHandler
```

---

# 🗄️ Database Schema

### 👤 User
- id (Primary Key)
- username
- email
- password
- fullName

### 📝 Post
- id
- caption
- imageUrl
- user_id (Foreign Key)

### 💬 Comment
- id
- content
- post_id (Foreign Key)

### ❤️ Like
- id
- user_id (Foreign Key)
- post_id (Foreign Key)

---

# 🔗 API Endpoints

## 👤 User

POST /users  
GET /users  
GET /users/by-email?email=value  

## 📝 Post

POST /posts  
GET /posts  
GET /posts/user/{userId}  
DELETE /posts/{id}  

## 💬 Comment

POST /comments  
DELETE /comments/{id}  

## ❤️ Like

POST /likes  
DELETE /likes/{id}  

---

# 🔒 Validation

Uses:

- @NotBlank
- @Email
- @Size
- @Valid

Invalid requests return structured 400 responses.

---

# ⚙️ How to Run

1️⃣ Clone the repository  
git clone <your-repo-url>

2️⃣ Configure PostgreSQL in application.properties

```
spring.datasource.url=jdbc:postgresql://localhost:5432/postgres
spring.datasource.username=postgres
spring.datasource.password=your_password
spring.jpa.hibernate.ddl-auto=update
```

3️⃣ Run the application

mvn spring-boot:run

Server starts at:

http://localhost:8080

---

# 🧠 Tech Stack

- Spring Boot
- Spring Data JPA
- Hibernate ORM
- PostgreSQL
- Lombok
- Validation API
- Maven

---

# 🚀 Future Enhancements

- JWT Authentication
- BCrypt Password Encryption
- Pagination
- DTO Layer
- Swagger Documentation
- Cloud Deployment

---

<p align="center">
  <img src="https://capsule-render.vercel.app/api?type=waving&color=0:92FE9D,100:00C9FF&height=120&section=footer"/>
</p>

---

<p align="center">
🔥 Built with Passion & Clean Code Principles 🔥
</p>
