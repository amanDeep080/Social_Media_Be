<p align="center">
  <img src="https://capsule-render.vercel.app/api?type=waving&color=0:00C9FF,100:92FE9D&height=200&section=header&text=Social%20Media%20Backend%20API&fontSize=40&fontColor=ffffff&animation=fadeIn&fontAlignY=35"/>
</p>

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
src/main/java/com/example/demo
│
├── controller
├── service
├── repository
├── model
└── exception

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
2️⃣ Configure PostgreSQL in application.properties  
3️⃣ Run:
mvn spring-boot:run
