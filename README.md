# User-Manager
Used to handle user management with JWT

## Steps to Run

**1. Clone the repository** 


**2. Configure MySQL database**

Create a MySQL database named `test`, and change the username and password in `src/main/resources/application.properties` as per your MySQL
installation -

```properties
spring.datasource.username= root
spring.datasource.password= root
```

**3. Run the app using maven**

```bash
mvn spring-boot:run
```

That's it! The application can be accessed at `http://localhost:9090`.
