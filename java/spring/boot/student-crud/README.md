## Student-CRUD
This project is built with spring boot to practice CRUD operations in spring boot.

## Requirements
1. Should be able to create, get, update, delete students. 
2. Any validation failure in the API should return proper error message.

## APIs
| Route                             | Method | Description                             |
|-----------------------------------|--------|-----------------------------------------|
| `/api/students`                   | POST   | Create a student                        |
| `/api/students`                   | GET    | Get list of students                    |
| `/api/students/{rollNumber}`      | GET    | Get a student with `rollNumber`         |
| `/api/students/{rollNumber}`      | PUT    | Update a single student                 |
| `/api/students/{rollNumber}`      | DELETE | Hard Delete a student with `rollNumber` |

## Models
1. Student(rollNumber, email, name, age, skills, createdAt, updatedAt)

## CURL
```bash
# Create Student
curl -v -X POST http://localhost:8080/api/students \
-H "Content-Type: application/json" \
-d '{"rollNumber":1,"name":"Pradeep","email":"pradeep@gmail.com","skills":["Java","Spring"],"age":22}'

# Get Student
curl -v -X GET http://localhost:8080/api/students/1

# Get All Students
curl -v -X GET http://localhost:8080/api/students

# Update Student
curl -v -X PUT http://localhost:8080/api/students/1 \
-H "Content-Type: application/json" \
-d '{"rollNumber":1,"name":"Pradeep Kumar","email":"pradeep@gmail.com","skills":["Java","Spring"],"age":22}'

# Delete Student
curl -v -X DELETE http://localhost:8080/api/students/1
```
## TODO
- [x] Use appropriate DTOs
- [x] Handle validations with `spring-boot-starter-validation`
- [x] Add Proper Exception Handling
- [ ] Add mapper classes
