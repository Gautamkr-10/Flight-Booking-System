# Flight Booking System ✈️
Developed a backend service for managing flights, passengers, and bookings using a layered architecture. Implemented booking workflows with seat validation, cancellation logic, and relational mapping between entities using JPA/Hibernate. Ensured consistent API output through a reusable ResponseStructure and handled errors using custom exceptions. Designed scalable, maintainable modules following Controller → Service → Repository design.
---

## 🚀 Features

- Flight Management  
  - Add, view, and manage flight records  
  - Total seat tracking  

- Passenger & Booking Management  
  - Create bookings with multiple passengers  
  - Seat validation based on availability  
  - Assign flights to bookings  
  - Cancel bookings and release seats  

- Standardized API Responses  
  - Uses a generic ResponseStructure<T> to send consistent JSON responses  

- Exception Handling  
  - Custom exceptions for invalid flight ID, empty passenger list, missing data, etc.  

- Layered Architecture  
  - Clean separation of Controller → Service → Repository  
  - Improves readability, maintainability, and scalability
 
- Email Notification
  - Flight booking with passenger and payment validation
  - Email confirmation with detailed flight and passenger information
    

---

## 🏗️ Technologies Used

- Java 17+  
- Spring Boot  
- Spring Data JPA / Hibernate  
- Lombok  
- PostgreSQL (Database)

---

## 📂 Project Structure
```
│
├── controller/ → REST Controllers
├── service/ → Business Logic Layer
├── repository/ → JPA Repositories
├── entity/ → JPA Entities (Flight, Booking, Passenger)
├── exception/ → Custom Exceptions
└── dto/ → Response Structure and DTO classes
```


---

## 🔧 Key Endpoints

| Endpoint              | Method | Description                      |
|----------------------|--------|----------------------------------|
| /booking             | POST   | Create a booking                 |
| /booking/{id}        | GET    | Get booking by ID                |
| /booking/all         | GET    | Get all bookings                 |
| /booking/cancel/{id} | PUT    | Cancel a booking                 |
| /flight/{id}         | GET    | Get flight by ID                 |
| /flight              | POST   | Add a new flight                 |

---

## 📘 Core Classes

### Booking  
Stores flight details, passenger list, booking time, and status.

### Flight  
Holds flight information such as flight ID, name, and total seats.

### Passenger  
Represents passenger data linked to a booking.

### ResponseStructure  
Generic wrapper for clean API responses.

### Exceptions  
Includes NoRecordFoundException and global exception handlers.

---

## 🧪 How to Run

### 1. Clone the repository
`git clone https://github.com/Gautamkr-10/Flight-Booking-System.git`
### 3. Configure Database
```
In application.properties:
spring.datasource.url=jdbc:mysql://localhost:3306/flightdb
spring.datasource.username=root
spring.datasource.password=yourpassword
spring.jpa.hibernate.ddl-auto=update
```


### 4. Run the Application

Run the main class:FlightBookingSystemApplication.java
or
```
mvn spring-boot:run
```

### 5. Access APIs

- Use Postman or Swagger
---

## 🧹 Future Enhancements

- JWT-based Authentication and Authorization  
- Search flights by date, source, destination  
- Email/SMS booking confirmation  
- Admin dashboard  
- React/Angular frontend integration  

---



