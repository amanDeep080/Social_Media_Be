<p align="center">
  <img src="https://media.giphy.com/media/qgQUggAC3Pfv687qPC/giphy.gif" width="100%" alt="Animated Coding Banner"/>
</p>

<h1 align="center">🚀 Social Media Backend API</h1>

<p align="center">
  <b>Spring Boot + PostgreSQL + JPA + Validation</b><br>
  A Production-Ready REST API built with Clean Layered Architecture
</p>

<p align="center">
  <img src="https://img.shields.io/badge/Backend-SpringBoot-green?style=for-the-badge"/>
  <img src="https://img.shields.io/badge/Database-PostgreSQL-blue?style=for-the-badge"/>
  <img src="https://img.shields.io/badge/ORM-Hibernate-orange?style=for-the-badge"/>
  <img src="https://img.shields.io/badge/Architecture-Layered-success?style=for-the-badge"/>
</p>

---

# 📌 Project Overview

This is a fully functional Social Media Backend API built using:

- Spring Boot
- Spring Data JPA (Hibernate ORM)
- PostgreSQL
- Lombok
- Validation API

The project follows clean layered architecture:

Controller → Service → Repository → Database

---

# 🧠 Key Features

✅ Create & Manage Users  
✅ Create Posts linked to Users  
✅ Add Comments to Posts  
✅ Like / Unlike Posts  
✅ Validation using `@Valid`, `@NotBlank`, `@Email`  
✅ Proper HTTP Status Codes (201, 200, 400, 404, 204)  
✅ Global Exception Handling  
✅ Clean REST API Design  

---

# 🗂️ Project Structure
src/main/java/com/example/demo
│
├── controller
│ ├── UserController
│ ├── PostController
│ ├── CommentController
│ └── LikeController
│
├── service
│ ├── UserService
│ ├── PostService
│ ├── CommentService
│ └── LikeService
│
├── repository
│ ├── UserRepo
│ ├── PostRepo
│ ├── CommentRepo
│ └── LikeRepo
│
├── model
│ ├── User
│ ├── Post
│ ├── Comment
│ └── Like
│
└── exception
├── ApiError
├── BadRequestException
├── NotFoundException
└── GlobalExceptionHandler

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

## 👤 User APIs

### ➤ Create User
POST /users

```json
{
  "username": "aman01",
  "email": "aman01@gmail.com",
  "password": "secret123",
  "fullName": "Amandeep Kumar"
}
Returns: 201 Created
