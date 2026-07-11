### Start Server - UviCorn

```bash
fastapi dev bookly/2.simple-crud/main.py
```

### Requests
```bash

# Get All Books
curl -X GET http://localhost:8000/books

# Get a Book
curl -X GET http://localhost:8000/books/1

# Add Book
curl -X POST http://localhost:8000/books \
     -H "Content-Type: application/json" \
     -d '{"name" : "NewComedyBook-1", "desc": "NewComedyBookDesc-1", "genre": "comedy"}'

# Update a Book
curl -X PATCH http://localhost:8000/books/5 \
     -H "Content-Type: application/json" \
     -d '{"name" : "UpdatedNewBook-1", "desc": "UpdatedNewBookDesc-1", "genre": "comedy"}'

# Delete a Book
curl -X DELETE http://localhost:8000/books/5
```