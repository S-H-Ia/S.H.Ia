# Spring Social Network API

Mini social network backend for universities and mid-sized companies. Users have public profiles enriched with categorized tags. Profiles are designed to feed a semantic similarity search via embeddings (in progress).

---

## Tech Stack

| Layer | Tech |
|---|---|
| Language | Java 25 |
| Framework | Spring Boot 4 |
| Security | Spring Security (HTTP Basic) |
| Database | PostgreSQL 16 + pgvector |
| Migrations | Flyway |
| HTTP Client | Spring WebFlux (WebClient) |
| Boilerplate | Lombok |
| Build | Maven Wrapper |

---

## Architecture

```
src/main/java/kani/springsecurity/
├── Application/
│   ├── Controller/     # REST endpoints — delegates only, no business logic
│   ├── Request/        # Input DTOs (records)
│   ├── Response/       # Output DTOs (records)
│   └── Mapper/         # Entity ↔ DTO conversion
├── Domain/
│   ├── Users/          # User entity + UserService (implements UserDetailsService)
│   ├── Profile/        # UserProfile entity + ProfileService
│   ├── Tags/           # Tag entity + TagService
│   └── Embeding/       # EmbedingService — calls Python microservice via WebClient
└── Infra/
    ├── SecurityConfig  # Spring Security config
    └── WebClientConfig # WebClient bean
```

**Key design decisions:**
- `User` — sensitive data (password, role). Never returned directly from controllers.
- `UserProfile` — public data (bio, location, occupation, interests, tags). Exposed via DTOs.
- `Tag` — pre-seeded catalog of 37 tags grouped by category (`area`, `habilidade`, `objetivo`, `perfil`).
- Schema is 100% Flyway-controlled. `hibernate.ddl-auto = validate` — Hibernate never touches the schema.
- Embedding generation is delegated to an external Python microservice via reactive WebClient.

---

## Prerequisites

- Java 25+
- Docker + Docker Compose
- Maven (or use the included `./mvnw`)

---

## Running

**1. Start the database**

```bash
docker compose -f docker-composer.yml up -d
```

Spins up PostgreSQL 16 with pgvector on port `5432`, database `users_agent`.

**2. Set environment variables**

```bash
export DB_USERNAME=root
export DB_PASSWORD=root
export API_KEY=your_anthropic_key  # not used yet
```

**3. Run the app**

```bash
./mvnw spring-boot:run
```

App starts on `http://localhost:8080`.

---

## API Endpoints

Authentication: HTTP Basic.

### Users

| Method | Endpoint | Description |
|---|---|---|
| GET | `/users/` | List all users |
| GET | `/users/{id}` | Get user by ID |
| POST | `/users/` | Create user |
| PUT | `/users/{id}` | Update user |
| DELETE | `/users/{id}` | Delete user + profile |

### Profiles

| Method | Endpoint | Description |
|---|---|---|
| GET | `/users/profile/{id}` | Get public profile |
| PUT | `/users/profile/{id}` | Update profile |

### Tags

| Method | Endpoint | Description |
|---|---|---|
| GET | `/tags` | List all tags grouped by category |
| POST | `/tags/{profileId}` | Add tag to profile |

### Embeddings

| Method | Endpoint | Description |
|---|---|---|
| POST | `/embed/{profileId}` | Generate profile embedding via microservice |

---

## Database Migrations

Three Flyway migrations applied on startup:

| Version | What it does |
|---|---|
| V1 | Creates `users`, `user_profiles`, `tag`, `profile_tags` tables |
| V2 | Refactors `user_profiles` — removes `age`/`magic_place`, adds `location`/`occupation`/`interests` |
| V3 | Seeds 37 tags across 4 categories |

---

## Tests

```bash
# Run all tests
./mvnw test

# Run specific test
./mvnw test -Dtest=SpringSecurityApplicationTests#contextLoads
```

---

## Roadmap

- [ ] Semantic profile search using pgvector similarity queries
- [ ] Python microservice integration for token-optimized embeddings
- [ ] Role-based access control (ADMIN vs USER)
- [ ] Proper global exception handling
