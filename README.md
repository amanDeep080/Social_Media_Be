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


{
  "username": "aman01",
  "email": "aman01@gmail.com",
  "password": "secret123",
  "fullName": "Amandeep Kumar"
}
Returns: 201 Created
➤ Get All Users
GET /users
Returns: 200 OK
➤ Get User by Email
GET /users/by-email?email=aman01@gmail.com
Returns: 200 OK
📝 Post APIs
➤ Create Post
POST /posts
{
  "caption": "My first post",
  "imageUrl": "https://image.com/photo.jpg",
  "user": { "id": 1 }
}
Returns: 201 Created
➤ Get All Posts
GET /posts
➤ Get Posts by User
GET /posts/user/{userId}

Example:

GET /posts/user/1
➤ Delete Post
DELETE /posts/{id}

Returns: 204 No Content

💬 Comment APIs
➤ Add Comment
POST /comments
{
  "content": "Nice post!",
  "post": { "id": 1 }
}

Returns: 201 Created

➤ Delete Comment
DELETE /comments/{id}

Returns: 204 No Content

❤️ Like APIs
➤ Like a Post
POST /likes
{
  "user": { "id": 1 },
  "post": { "id": 1 }
}
➤ Unlike Post
DELETE /likes/{id}

Returns: 204 No Content

🔒 Validation

This project uses:

@NotBlank

@Email

@Size

@Valid

Invalid requests return:

400 Bad Request

With structured JSON error response.

⚙️ How to Run the Project
1️⃣ Clone Repository
git clone <your-repo-url>
2️⃣ Configure PostgreSQL

Update application.properties:

spring.datasource.url=jdbc:postgresql://localhost:5432/postgres
spring.datasource.username=postgres
spring.datasource.password=your_password
spring.jpa.hibernate.ddl-auto=update
3️⃣ Run Application
mvn spring-boot:run

Server runs on:

http://localhost:8080
🧠 Architecture Highlights

✔ Clean Layered Architecture
✔ Constructor-Based Dependency Injection
✔ ORM using Hibernate
✔ Entity Relationships (OneToMany, ManyToOne)
✔ Proper REST Standards
✔ Professional Error Handling

🚀 Future Enhancements

JWT Authentication

Password Encryption (BCrypt)

Pagination

DTO Layer

Swagger Documentation

Deployment on Render / Railway

👨‍💻 Author

Amandeep Kumar
Java | Spring Boot | Backend Developer

<p align="center"> 🔥 Built with Passion & Clean Code Principles 🔥 </p> ```
