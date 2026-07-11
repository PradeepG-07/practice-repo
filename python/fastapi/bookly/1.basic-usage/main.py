from fastapi import FastAPI, Header, Body, Form, Request

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

## Body
## Add a book

# 1. Body with JSON as Dict
# @app.post("/books")
# def add_a_book(payload: dict = Body({})) -> dict :
#     book_name = payload.get("name")
#     book_desc = payload.get("desc")
#     book_genre = payload.get("genre")

#     if book_name is None or book_desc is None or book_genre is None:
#         return {
#             "message": "Invalid Payload"
#         }

#     book_id = len(books) + 1
#     book = {
#         "id": book_id,
#         "name" : book_name,
#         "desc": book_desc,
#         "genre": book_genre
#     }

#     books.append(book)

#     return book

# 2. Body with FormData
# @app.post("/books")
# def add_a_book(
#     book_name: str = Form(None, alias="name"), 
#     book_desc: str = Form(None, alias="desc"), 
#     book_genre: str = Form(None, alias="genre")
# ) -> dict :

#     if book_name is None or book_desc is None or book_genre is None:
#         return {
#             "message": "Invalid Payload"
#         }

#     book_id = len(books) + 1
#     book = {
#         "id": book_id,
#         "name" : book_name,
#         "desc": book_desc,
#         "genre": book_genre
#     }

#     books.append(book)

#     return book

# 3. Access raw request body
@app.post("/books")
async def add_a_book(
    request: Request
) -> dict :
    
    payload: dict = await request.json()

    book_name = payload.get("name")
    book_desc = payload.get("desc")
    book_genre = payload.get("genre")

    if book_name is None or book_desc is None or book_genre is None:
        return {
            "message": "Invalid Payload"
        }

    book_id = len(books) + 1
    book = {
        "id": book_id,
        "name" : book_name,
        "desc": book_desc,
        "genre": book_genre
    }

    books.append(book)

    return book