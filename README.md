<div align="center">

<img src="https://img.shields.io/badge/GitInsight-1.0.0-1a9e75?style=for-the-badge&logo=github&logoColor=white" alt="GitInsight"/>

# GitInsight

**GitHub user analytics — profiles, repositories, and activity at a glance.**

[![Java](https://img.shields.io/badge/Java_21-ED8B00?style=flat-square&logo=openjdk&logoColor=white)](https://openjdk.org/projects/jdk/21/)
[![Spring Boot](https://img.shields.io/badge/Spring_Boot_3-6DB33F?style=flat-square&logo=springboot&logoColor=white)](https://spring.io/projects/spring-boot)
[![MariaDB](https://img.shields.io/badge/MariaDB-003545?style=flat-square&logo=mariadb&logoColor=white)](https://mariadb.org/)
[![Redis](https://img.shields.io/badge/Redis-DC382D?style=flat-square&logo=redis&logoColor=white)](https://redis.io/)
[![Docker](https://img.shields.io/badge/Docker-2496ED?style=flat-square&logo=docker&logoColor=white)](https://www.docker.com/)
[![Swagger](https://img.shields.io/badge/Swagger-85EA2D?style=flat-square&logo=swagger&logoColor=black)](https://swagger.io/)

</div>

---

## Overview

GitInsight is a **Spring Boot** application that tracks GitHub users and provides analytics about their profiles, repositories, and activity. It integrates with the GitHub API, stores tracked users in a database, and uses Redis for caching and rate limiting.

---

## Features

- 🔍 Fetch GitHub user profiles, repositories, and recent events
- 📊 Generate user summary analytics (top language, forks, activity)
- 💾 Track and persist GitHub users in a MariaDB database
- ⚡ Redis caching for faster responses and reduced API calls
- 🚦 Redis-based token bucket rate limiting
- 🔄 Asynchronous bulk user data refresh with `@Async`
- 🕒 Scheduled background jobs to keep tracked user data fresh
- 🐳 Dockerized deployment
- 📄 Swagger / OpenAPI documentation
- ✅ Global exception handling and input validation

---

## Architecture

```
Client → Spring Boot API → GitHub REST API
Client → Spring Boot API → Redis Cache
Client → Spring Boot API → MariaDB (via Spring Data JPA)
```

---

## Tech Stack

| Layer | Technology |
|---|---|
| Language | Java 21 |
| Framework | Spring Boot 3 |
| ORM | Spring Data JPA |
| Database | MariaDB |
| Cache / Rate Limiting | Redis / Valkey |
| Async Processing | Spring `@Async` |
| Build Tool | Maven |
| Containerization | Docker |
| API Docs | Swagger / OpenAPI |

---

## API Endpoints

### GitHub

| Method | Endpoint | Description |
|---|---|---|
| `GET` | `/api/github/users/{username}` | Fetch a GitHub user profile |
| `GET` | `/api/github/users/{username}/repos` | Fetch a user's repositories |
| `GET` | `/api/github/users/{username}/events` | Fetch a user's recent events |

### Tracked Users

| Method | Endpoint | Description |
|---|---|---|
| `POST` | `/api/tracked-users/{username}` | Start tracking a GitHub user |
| `DELETE` | `/api/tracked-users/{username}` | Stop tracking a GitHub user |

### Analytics

| Method | Endpoint | Description |
|---|---|---|
| `GET` | `/api/summary/{username}` | Get analytics summary for a user |

**Summary response includes:**
- Followers & Following count
- Public repositories count
- Most used programming language
- Total forks across repositories
- Recent activity count

---

## Performance

### Redis Caching
Frequently requested GitHub profiles are cached using **Spring Cache + Redis**, reducing external API calls and improving response times.

### Rate Limiting
A **Token Bucket Rate Limiter** is implemented using Redis, protecting the application from excessive requests per client.

### Async Processing
Bulk refresh operations run asynchronously using Spring's `@Async` support, keeping the API non-blocking during heavy updates.

### Scheduled Updates
Background jobs run on a schedule to periodically refresh all tracked users' data without manual intervention.

---

## Getting Started

### Prerequisites

- Java 21+
- Maven 3.8+
- MariaDB
- Redis / Valkey
- Docker (optional)

### Clone & Run

```bash
# Clone the repository
git clone https://github.com/saidepak111-sudo/gitinsight.git
cd gitinsight

# Build the project
mvn clean install

# Run the application
mvn spring-boot:run
```

### Docker

```bash
# Build the Docker image
docker build -t gitinsight .

# Run the container
docker run -p 8080:8080 gitinsight
```

### API Documentation

Once running, visit Swagger UI at:

```
http://localhost:8080/swagger-ui.html
```

---

## Roadmap

- [ ] JWT Authentication
- [ ] OAuth2 Login
- [ ] GitHub Webhooks integration
- [ ] Kafka Event Streaming
- [ ] Frontend Dashboard
- [ ] CI/CD Pipeline

---

## Author

<div align="center">

**Sai Deepak**  
NIT Calicut — Computer Science Engineering  
*Backend Development · DSA · System Design*

[![GitHub](https://img.shields.io/badge/GitHub-saidepak111--sudo-181717?style=flat-square&logo=github)](https://github.com/saidepak111-sudo)

</div>

---

<div align="center">
<sub>Built with ☕ and Spring Boot</sub>
</div>
