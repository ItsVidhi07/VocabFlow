# VocabFlow

### Build a vocabulary that stays with you.

## Live Demo

https://vocabflow-production.up.railway.app

VocabFlow is a full-stack vocabulary learning web application designed to help users discover, learn, save, and practice new words through an interactive learning experience.

The application combines a Java Spring Boot backend with a responsive HTML, CSS, and JavaScript frontend and uses MySQL for persistent user and vocabulary data.

---

## ✨ Features

* 🔐 **User Authentication**

  * User registration
  * Login using username or email
  * BCrypt password hashing
  * Session-based authentication
  * Logout functionality

* 📚 **Vocabulary Library**

  * 100+ curated vocabulary words
  * Word meanings
  * Parts of speech
  * Example sentences
  * Synonyms
  * Difficulty levels
  * Categories

* 🔎 **Word Search**

  * Search vocabulary dynamically through the backend API
  * Case-insensitive word matching

* 🧠 **Active Learning**

  * Learn words through an interactive learning interface
  * Mark words as learned
  * Track learning progress

* ❤️ **Saved Words**

  * Save words for later revision
  * Remove saved words when no longer needed

* 🎯 **Daily Goal**

  * Daily target of 5 words
  * Dynamic progress indicator
  * Automatically resets for a new day

* 📝 **Vocabulary Quiz**

  * Multiple-choice questions
  * Instant answer feedback
  * Score tracking
  * Accuracy tracking
  * Restartable quizzes

* 📊 **Progress Tracking**

  * Words learned
  * Saved words
  * Quiz accuracy
  * Overall vocabulary progress

---

## 🛠️ Tech Stack

### Frontend

* HTML5
* CSS3
* JavaScript
* Fetch API
* Local Storage

### Backend

* Java
* Spring Boot
* Spring Web
* Spring Data JPA
* REST APIs

### Database

* MySQL
* Hibernate / JPA

### Security

* BCrypt password hashing
* HTTP session-based authentication

### Build Tool

* Apache Maven

---

## 🏗️ Project Architecture

```text
VocabFlow
│
├── src/main/java/com/example/vocabflow
│   │
│   ├── controller
│   │   ├── AuthController.java
│   │   └── WordController.java
│   │
│   ├── service
│   │   ├── AuthService.java
│   │   └── WordService.java
│   │
│   ├── repository
│   │   ├── UserRepository.java
│   │   └── WordRepository.java
│   │
│   ├── entity
│   │   ├── User.java
│   │   └── Word.java
│   │
│   ├── DataInitializer.java
│   └── VocabFlowApplication.java
│
├── src/main/resources
│   │
│   └── static
│       ├── index.html
│       ├── login.html
│       ├── register.html
│       ├── dashboard.html
│       │
│       ├── css
│       │   └── style.css
│       │
│       └── js
│           ├── app.js
│           └── auth.js
│
├── pom.xml
└── README.md
```

---

## 🔄 How It Works

```text
User
  ↓
VocabFlow Frontend
  ↓
JavaScript Fetch API
  ↓
Spring Boot REST API
  ↓
Service Layer
  ↓
JPA Repository
  ↓
MySQL Database
```

The frontend communicates with the Spring Boot backend through REST endpoints.

For example:

```text
GET /api/words
```

retrieves vocabulary from the backend.

Search requests use:

```text
GET /api/words/search?query=...
```

Authentication is handled through:

```text
POST /api/auth/register
POST /api/auth/login
POST /api/auth/logout
GET  /api/auth/session
```

---

## 🗄️ Database

VocabFlow currently uses two main database entities.

### Users

Stores registered user information:

* ID
* Full name
* Username
* Email
* BCrypt-hashed password

### Words

Stores vocabulary information:

* ID
* Word
* Part of speech
* Meaning
* Example sentence
* Synonyms
* Difficulty
* Category

The application automatically creates and updates the required database tables through JPA/Hibernate.

---

## 🚀 Running the Project Locally

### Prerequisites

Make sure the following are installed:

* Java 17 or later
* Maven
* MySQL
* Git

### 1. Clone the repository

```bash
git clone https://github.com/ItsVidhi07/VocabFlow.git
cd VocabFlow
```

### 2. Create the database

Open MySQL and run:

```sql
CREATE DATABASE vocabflow_db;
```

### 3. Configure MySQL

Create:

```text
src/main/resources/application.properties
```

and configure:

```properties
spring.datasource.url=jdbc:mysql://localhost:3306/vocabflow_db?useSSL=false&serverTimezone=UTC&allowPublicKeyRetrieval=true
spring.datasource.username=root
spring.datasource.password=YOUR_MYSQL_PASSWORD

spring.jpa.hibernate.ddl-auto=update
spring.jpa.show-sql=true
spring.jpa.properties.hibernate.dialect=org.hibernate.dialect.MySQLDialect

server.port=8080
```

Replace `YOUR_MYSQL_PASSWORD` with your local MySQL password.

### 4. Run the application

Using Maven:

```bash
mvn spring-boot:run
```

### 5. Open VocabFlow

Visit:

```text
http://localhost:8080/
```

---

## 📌 API Endpoints

### Vocabulary

| Method | Endpoint                   | Purpose             |
| ------ | -------------------------- | ------------------- |
| GET    | `/api/words`               | Get all vocabulary  |
| GET    | `/api/words/{id}`          | Get a specific word |
| GET    | `/api/words/search?query=` | Search vocabulary   |

### Authentication

| Method | Endpoint             | Purpose               |
| ------ | -------------------- | --------------------- |
| POST   | `/api/auth/register` | Create an account     |
| POST   | `/api/auth/login`    | Sign in               |
| POST   | `/api/auth/logout`   | Sign out              |
| GET    | `/api/auth/session`  | Check current session |

---

## 🔮 Future Improvements

* User-specific learning progress stored in MySQL
* Spaced repetition algorithm
* Personalized vocabulary recommendations
* Pronunciation and audio support
* More advanced quiz modes
* Vocabulary streak history
* PWA support for mobile installation
* Cloud deployment
* More detailed learning analytics

---

## 👩‍💻 Project

**VocabFlow** is a full-stack Java web application created as an academic software project while exploring backend development, REST APIs, databases, authentication, and interactive frontend development.

Built with Java, Spring Boot, MySQL, HTML, CSS and JavaScript.
