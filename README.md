Sure! Here's the previous `README.md` for the Uber Backend Spring Boot application:

---

# 🚖 Uber Backend Spring Boot Application

Welcome to the **Uber Backend Spring Boot Application**, a fully functional backend for an Uber-like ride-hailing service! This project implements features such as user authentication, ride request management, driver-rider matching, with Spring Boot and PostGIS for geospatial data.

---

## 🌟 Features

- **User Authentication & Authorization**
  - Role-based JWT authentication (Rider, Driver, Admin).
  - User registration and login with Spring Security.
  
- **Ride Management**
  - Request, accept, cancel, complete, rate rides.
  - Driver and rider matching based on geolocation (PostGIS).

- **Driver & Rider Management**
  - Driver verification workflow for admins.
  - Ride history and status management.

- **Payments & Transactions**
  - Tracking each transcation and also implemented wallet for each user.
  
- **API Documentation**
  - Interactive API documentation with Swagger.

---

## 🚀 Technology Stack

| Technology               | Description                                        |
|--------------------------|----------------------------------------------------|
| **Spring Boot**           | Java-based framework for building application.   |
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
- **PostgreSQL 13** or later(with PostGIS extension)

---

## 📦 Getting Started

### 1. **Clone the Repository**

```bash
git clone https://github.com/yourusername/UberApp.git
cd UberApp
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
## Here is a full API Endpoints of all API path (Open it in PostMan)


Check out the full API docs at `/swagger-ui.html`!

---

## 📊 Database Schema

This project uses PostgreSQL along with **PostGIS** to handle geospatial queries for finding the nearest drivers and tracking ride locations. Here’s a quick preview of the key tables:

- **Users**: Stores user information (riders, drivers, admins).
- **Rides**: Stores ride details including start and end locations (geospatial data).
- **Drivers**: Driver-specific information like vehicle details.
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
