# Smart-Interview-Preparation-Portal

A full-stack web application to help users prepare for interviews by job role, topic, difficulty, and company patterns.

## Tech Stack

- Frontend: React, React Router, Axios, Bootstrap, Context API
- Backend: Spring Boot, Spring Security, JWT, Spring Data JPA, H2, Lombok

## Features

- JWT authentication (register, login, logout)
- Dashboard with progress metrics and quick actions
- Role-based interview question browsing
- Search and filters by keyword, topic, company, difficulty, and frequent tags
- Favorite and completed question tracking
- Progress analytics with charts (completed vs remaining, topic-wise)

## Project Structure

- `frontend/` - React client
- `backend/` - Spring Boot API server

## Run Locally

### Backend

```bash
cd backend
./mvnw spring-boot:run
```

### Frontend

```bash
cd frontend
npm install
npm run dev
```

Frontend runs on `http://localhost:5173` and backend runs on `http://localhost:8080`.
