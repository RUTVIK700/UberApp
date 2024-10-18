Sure! Here's the previous `README.md` for the Uber Backend Spring Boot application:

---

# 🚖 Uber Backend Spring Boot Application

Welcome to the **Uber Backend Spring Boot Application**, a fully functional backend for an Uber-like ride-hailing service! This project implements features such as user authentication, ride request management, driver-rider matching, and real-time tracking with Spring Boot and PostGIS for geospatial data.

---

## 🌟 Features

- **User Authentication & Authorization**
  - Role-based JWT authentication (Rider, Driver, Admin).
  - User registration and login with Spring Security.
  
- **Ride Management**
  - Request, accept, and complete rides.
  - Driver and rider matching based on geolocation (PostGIS).

- **Real-time Location Tracking**
  - Real-time updates on driver and rider location using geospatial data.

- **Driver & Rider Management**
  - Driver verification workflow for admins.
  - Ride history and status management.

- **Payments & Transactions**
  - Integration with payment gateways (e.g., Stripe or PayPal).

- **Admin Dashboard**
  - Admin functionality to monitor and manage the platform.
  
- **API Documentation**
  - Interactive API documentation with Swagger.

---

## 🚀 Technology Stack

| Technology               | Description                                        |
|--------------------------|----------------------------------------------------|
| **Spring Boot**           | Java-based framework for building microservices.   |
| **REST API**              | Build and expose RESTful services for interaction. |
| **Spring Security & JWT** | Secure APIs with role-based access control.        |
| **Hibernate (JPA)**       | ORM to handle database interaction with entities.  |
| **PostgreSQL & PostGIS**  | PostgreSQL database with PostGIS for geolocation.  |
| **Swagger API**           | Generate and visualize API documentation.          |

---

## 🛠️ Prerequisites

Make sure you have the following installed:

- **Java 17** or later
- **Maven 3.8+**
- **PostgreSQL 13** (with PostGIS extension)

---

## 📦 Getting Started

### 1. **Clone the Repository**

```bash
git clone https://github.com/yourusername/uber-backend-springboot.git
cd uber-backend-springboot
```

### 2. **Configure the Application**

Update the `src/main/resources/application.properties` file with your database configurations:

```properties
# Database configuration
spring.datasource.url=jdbc:postgresql://localhost:5432/uber_db
spring.datasource.username=your-username
spring.datasource.password=your-password

# JPA Hibernate configuration
spring.jpa.hibernate.ddl-auto=update
spring.jpa.show-sql=true

# JWT properties
jwt.secret=your-jwt-secret
jwt.expiration=86400000  # Token expiration in milliseconds

# Swagger UI properties
springdoc.swagger-ui.path=/swagger-ui.html
```

### 3. **Install PostgreSQL and PostGIS**

```bash
# Install PostgreSQL and PostGIS (for Ubuntu)
sudo apt update
sudo apt install postgresql postgresql-contrib postgis postgresql-13-postgis-3
```

Enable PostGIS on your database:

```sql
CREATE EXTENSION postgis;
```

### 4. **Build and Run the Application**

```bash
# Run with Maven
mvn clean install
mvn spring-boot:run
```

### 5. **Access the Application**

- **Swagger UI**: `http://localhost:8080/swagger-ui.html`
- **Admin Dashboard**: `http://localhost:8080/admin`
  
---

## 🧪 Running Tests

Run unit and integration tests using Maven:

```bash
mvn test
```

---

## 📖 API Endpoints

### Authentication

- **POST** `/api/auth/register` - Register a new user (driver/rider).
- **POST** `/api/auth/login` - Authenticate and receive a JWT token.

### Ride Management

- **POST** `/api/rides/request` - Request a new ride.
- **POST** `/api/rides/{id}/accept` - Driver accepts a ride.
- **POST** `/api/rides/{id}/complete` - Mark a ride as completed.

### Payment

- **POST** `/api/payment/checkout` - Handle ride payments.

Check out the full API docs at `/swagger-ui.html`!

---

## 📊 Database Schema

This project uses PostgreSQL along with **PostGIS** to handle geospatial queries for finding the nearest drivers and tracking ride locations. Here’s a quick preview of the key tables:

- **Users**: Stores user information (riders, drivers, admins).
- **Rides**: Stores ride details including start and end locations (geospatial data).
- **Drivers**: Driver-specific information like vehicle and license details.
- **Payments**: Handles transaction information for rides.

---

## 🤝 Contributing

We welcome contributions! If you'd like to contribute, please:

1. Fork the repository.
2. Create a new branch for your feature/bugfix.
3. Commit your changes and push your branch.
4. Submit a pull request.

---

## 📝 License

This project is licensed under the MIT License. See the [LICENSE](LICENSE) file for more details.

---

### Note

Make sure to update the configurations, feature list, and installation steps as per your specific implementation details!

---

This `README.md` is the same as the one provided earlier for the Uber-like backend application.
