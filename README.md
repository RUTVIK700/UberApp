
# 🚖 UberApp – Ride-Hailing Backend  
![Spring Boot](https://img.shields.io/badge/Spring%20Boot-6DB33F?style=for-the-badge&logo=spring-boot&logoColor=white)  
![PostGIS](https://img.shields.io/badge/PostGIS-4169E1?style=for-the-badge&logo=postgresql&logoColor=white)  
![Swagger](https://img.shields.io/badge/Swagger-85EA2D?style=for-the-badge&logo=swagger&logoColor=black)  

**UberApp** is a backend system designed to simulate a ride-hailing service. It features real-time ride requests, user management, geospatial data handling, and secure authentication using **Spring Security**.

---

## 📑 Table of Contents  
- [🚀 Features](#-features)  
- [🛠️ Tech Stack](#️-tech-stack)  
- [📦 Installation](#-installation)  
- [🗄️ Database Setup](#️-database-setup)  
- [▶️ Running the Project](#️-running-the-project)  
- [📋 API Documentation](#-api-documentation)  
- [📂 Project Structure](#-project-structure)  
- [🤝 Contributing](#-contributing)  


---

## 🚀 Features  
✨ **User Management**: Riders and Drivers can register and log in.  
🚗 **Ride Request Management**: Request, accept, and cancel rides.  
🌐 **Geospatial Queries**: Calculate routes, track locations, and find nearby drivers.  
💰 **Dynamic Fare Calculation**: Calculate trip fares based on distance and time.  
🔐 **Secure Endpoints**: Role-based access control using Spring Security.  
📄 **Swagger Documentation**: Interactive API testing with Swagger UI.  

---

## 🛠️ Tech Stack  
- **Backend Framework**: Spring Boot  
- **REST API Development**: Spring Boot  
- **Database**: PostgreSQL with PostGIS extension  
- **ORM**: Hibernate  
- **Database Layer**: Spring Data JPA  
- **Security**: Spring Security for authentication and authorization  
- **API Documentation**: Swagger UI  
- **Dependency Management**: Maven  

---

## 📦 Installation  

### Prerequisites  
- **Java 11+**  
- **PostgreSQL 13+** with **PostGIS** extension  
- **Maven 3+**  

### Clone the Repository  
```bash
git clone https://github.com/your-username/UberApp.git  
cd UberApp
```

### Install Dependencies  
```bash
mvn clean install
```

---

## 🗄️ Database Setup  

1. **Create PostgreSQL Database**  
   ```sql
   CREATE DATABASE uber_backend;
   ```

2. **Enable PostGIS Extension**  
   ```sql
   \c uber_backend;
   CREATE EXTENSION postgis;
   ```

3. **Configure Database Credentials** in `application.properties`  
   ```properties
   spring.datasource.url=jdbc:postgresql://localhost:5432/uber_backend
   spring.datasource.username=your_username
   spring.datasource.password=your_password
   spring.jpa.hibernate.ddl-auto=update
   spring.jpa.show-sql=true
   spring.security.enabled=true
   ```

---

## ▶️ Running the Project  

1. **Start PostgreSQL**.  
2. **Run the Spring Boot Application**:  
   ```bash
   mvn spring-boot:run
   ```  
The server will be available at **http://localhost:8080**.

---

## 📋 API Documentation  

### Swagger UI  
Access **Swagger UI** for detailed API documentation:  
[http://localhost:8080/swagger-ui/index.html](http://localhost:8080/swagger-ui/index.html)  

### Postman Collection  
Import the **Postman API collection** to explore and test the APIs:
[UBER_APP_TESTING.postman_collection.json](./UBER_APP_TESTING.postman_collection.json)  

To import:
1. Open **Postman**.
2. Click **Import** and upload the JSON file.
3. Explore and test the APIs directly in Postman.

---

## 📂 Project Structure  
```plaintext
uberapp/  
│  
├── src/main/java/com/yourname/uber  
│   ├── controller/        # API Controllers  
│   ├── dto/               # Data Transfer Objects  
│   ├── repository/        # JPA Repositories  
│   ├── security/          # Security Configurations and Filters  
│   ├── service/           # Business Logic Services  
│   ├── util/              # Utility Classes and Helpers 
│   └── UberBackendApplication.java # Main Application  
│  
├── src/main/resources/  
│   └── application.properties  # Configuration Properties  
│  
└── pom.xml                 # Maven Configuration  
```

---

## 🤝 Contributing  
1. **Fork** the repository.  
2. Create a new branch:  
   ```bash
   git checkout -b feature-branch
   ```  
3. Commit your changes:  
   ```bash
   git commit -m "Add feature"
   ```  
4. Push to the branch:  
   ```bash
   git push origin feature-branch
   ```  
5. Open a **Pull Request**.

---

## 🙌 Acknowledgments  
- **Spring Boot** for the backend framework.  
- **PostGIS** for geospatial data handling.  
- **Swagger** for interactive API documentation.  
- **Spring Security** for securing endpoints.  
- **Hibernate and JPA** for database operations.  

---
