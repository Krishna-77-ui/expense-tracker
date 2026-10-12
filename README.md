# Expense Tracker — React + Spring Boot + MySQL

A runnable full-stack starter with JWT authentication and CRUD expense management.

## Requirements
- Java 17+
- Maven 3.9+
- Node.js 20+
- MySQL 8+

## 1. Configure MySQL
Create a database:
```sql
CREATE DATABASE expense_tracker;
```
Update `backend/src/main/resources/application.properties` with your local MySQL username/password. The default is `root` / `password`; change these values for your machine. Hibernate creates/updates tables automatically (`ddl-auto=update`).

## 2. Run backend
```bash
cd backend
mvn spring-boot:run
```
Backend runs at `http://localhost:8080`.

## 3. Run frontend (new terminal)
```bash
cd frontend
npm install
npm run dev
```
Open the URL printed by Vite (normally `http://localhost:5173`).

## API overview
- `POST /api/auth/register` — `{ "name": "Krishna", "email": "krishna@example.com", "password": "Password123!" }`
- `POST /api/auth/login` — `{ "email": "krishna@example.com", "password": "Password123!" }`
- `GET /api/expenses` — list current user's expenses (JWT required)
- `POST /api/expenses` — create expense (JWT required)
- `GET /api/expenses/{id}` — get one (JWT required)
- `PUT /api/expenses/{id}` — update (JWT required)
- `DELETE /api/expenses/{id}` — delete (JWT required)
- `GET /api/expenses/summary` — total and category totals (JWT required)

Expense body example:
```json
{"title":"Groceries","amount":850.50,"category":"Food","expenseDate":"2026-10-11","notes":"Weekly shopping"}
```

## Security notes
This is a learning/demo project. Replace the development JWT secret in `application.properties` with a long random secret, keep credentials out of source control, and consider secure HttpOnly cookies instead of localStorage for production deployments. CORS currently allows localhost Vite development origins.
