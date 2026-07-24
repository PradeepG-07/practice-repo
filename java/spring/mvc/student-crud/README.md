## Student CRUD
> This project is for practicing CRUD only with spring mvc using a in- memory map as DB.

## Simple Request, Response Flow
### Request
> Client -> Tomcat -> DispatcherServlet -> Controller
### Response
> Controller -> ViewResolver/HttpResponseConverters -> DispatcherServlet -> Tomcat -> Client

## Annotations
1. @RestController(@Controller + @ResponseBody), @Service, @Repository
2. @RequestBody, @PathVariable
3. @GetMapping, @PostMapping, @PutMapping, @DeleteMapping
4. @Configuration, @ComponentScan, **@EnableWebMvc**(main annotation which enables the spring mvc)

## Classes
```java
// Tomcat Classes
class Tomcat{}
class Context{}

// IoC class
class AnnotationConfigWebApplicationContext{}

// DispatcherServlet
class DispatcherServlet{}
```

## Important Configurations
### 1. Base Tomcat Configuration
- Tomcat needs three main configurations
  - Which port to listen and what is the protocol?
  - A context path meaning a unique endpoint prefix for each web application deployed. In this case `student-crud`
  - A base doc where tomcat will look for the static files such as HTML, CSS, JavaScript and view files (.jsp, etc.).
  - Create a context in the tomcat with this `baseDoc` and `contextPath`.

### 2. IoC Configuration
- We can use `AnnotationConfigWebApplicationContext` as IoC container for Spring Web MVC.
- Use `register` method to register the configuration class of the web application.

### 3. MVC Configuration
- As part of MVC configuration the front controller `DispatcherServlet` should be configured and linked with the tomcat and the Controller methods. 
- **Step-1:** Initialize dispatcher servlet with IoC container to create the handler mappings. This phase links the `DispatcherServlet` with the controller methods.
- **Step-2:** Add `DispatcherServlet` to the earlier created tomcat context and add a mapping for all requests to this servlet.

### CURL 
```bash
# Create a student
curl -v -X POST http://localhost:8000/student-crud/api/students \
-H "Content-Type: application/json" \
-d '{"name":"Pradeep", "rollNumber": 1, "email":"pradeep@gmail.com"}'

# Get all students
curl -v -X GET http://localhost:8000/student-crud/api/students

# Get a student with rollNumber
curl -v -X GET http://localhost:8000/student-crud/api/students/1

# Update a student
curl -v -X PUT http://localhost:8000/student-crud/api/students/1 \
-H "Content-Type: application/json" \
-d '{"name":"Pradeep Kumar", "rollNumber": 1, "email":"pradeep123@gmail.com"}'

# Delete a student
curl -v -X DELETE http://localhost:8000/student-crud/api/students/1
```


