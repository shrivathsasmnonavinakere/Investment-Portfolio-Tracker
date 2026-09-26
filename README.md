# Investment Portfolio Tracker

A full-stack web application to track personal investments (stocks, mutual funds, fixed deposits) and monitor portfolio performance in real time.

## Features

- Add, view, and delete investment entries
- Automatic profit/loss calculation per investment
- Portfolio summary: total invested amount, current value, overall gain/loss, best and worst performing investment
- Filter investments by type (Stock, Mutual Fund, Fixed Deposit)
- Input validation and clean error handling on the backend

## Tech Stack

**Backend:** Java, Spring Boot, Spring Data JPA, Hibernate, MySQL
**Frontend:** HTML, CSS, JavaScript (Fetch API)
**Architecture:** Layered design with Controller, Service, Repository, and DTO layers; custom exception handling; request logging filter

## Project Structure

```
investment-tracker/
├── backend/      Spring Boot REST API
└── frontend/     HTML, CSS, JS client
```

## How to Run

1. Create a MySQL database named `investment_tracker`
2. Update `backend/src/main/resources/application.properties` with your MySQL username and password
3. Run `InvestmentTrackerApplication.java` (starts on `http://localhost:8080`)
4. Open `frontend/index.html` in your browser

## API Endpoints

| Method | Endpoint | Description |
|--------|----------|-------------|
| GET | /api/investments | Get all investments (optional `?type=` filter) |
| GET | /api/investments/summary | Get portfolio summary |
| POST | /api/investments | Add a new investment |
| PUT | /api/investments/{id} | Update an investment |
| DELETE | /api/investments/{id} | Delete an investment |
