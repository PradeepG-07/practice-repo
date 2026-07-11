### Start Server - UviCorn

```bash
fastapi dev bookly/1.basic-usage/main.py
```

### Requests
```bash

# Get All Books
curl -X GET http://localhost:8000/books

# Get Books Acc to Filters
curl -X GET http://localhost:8000/books/filter?genre=comedy
curl -X GET http://localhost:8000/books/filter?genre=comedy&limit=1

# Get Headers
curl -X GET http://localhost:8000/get-headers \
     -H "Content-Type: application/json" \
     -H "Accept: application/json"

# Add Book

#### Json Body
curl -X POST http://localhost:8000/books \
     -H "Content-Type: application/json" \
     -d '{"name" : "NewComedyBook-1", "desc": "NewComedyBookDesc-1", "genre": "comedy"}'

#### Multipart/FormData
curl -X POST http://localhost:8000/books \
     -F "name=NewHorrorBook-1" \
     -F "desc=NewHorrorBookDesc-1" \
     -F "genre=NewHorrorBookGenre-1"
```