from fastapi import FastAPI, Header

app = FastAPI()

books = [
    {
        "id": 1,
        "name" : "Book-1",
        "desc": "Desc-1",
        "genre": "thriller"
    },
    {
        "id": 2,
        "name" : "Book-2",
        "desc": "Desc-2",
        "genre": "thriller"
    },
    {
        "id": 3,
        "name" : "Book-3",
        "desc": "Desc-3",
        "genre": "comedy"
    },
    {
        "id": 4,
        "name" : "Book-4",
        "desc": "Desc-4",
        "genre": "comedy"
    },
]

## Basic Request
@app.get("/books")
def get_all_books() -> list:
    return books;

## Query Params - (Mandatory + Optional)
@app.get("/books/filter")
def get_filtered_books(genre: str, limit: int = 2):
    filtered_books = []
    cnt = 0
    for book in books:
        if cnt == limit: 
            break
        if book["genre"] == genre:
            filtered_books.append(book)
            cnt+=1
    return filtered_books


## Request Param
@app.get("/books/{id}")
def get_book(id: int):
    for book in books:
        if book["id"] == id:
            return book
    return None

## Headers
# Usage: Handler Param -> snake_case = Header(default_value)
@app.get("/get-headers")
def get_headers(
    accept = Header(None),
    user_agent = Header(None),
    content_type = Header(None)
) -> dict:
    request_headers = {}
    request_headers["Accept"] = accept
    request_headers["User-Agent"] = user_agent
    request_headers["Content-Type"] = content_type

    return request_headers