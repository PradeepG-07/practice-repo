from fastapi import FastAPI, Body, status, Response
from fastapi.responses import JSONResponse

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

## Get all books
@app.get("/books")
def get_all_books() -> list:
    return books;

## Get a book
@app.get("/books/{bookId}")
def get_a_book(bookId: int) -> dict:
    for book in books:
        if book["id"] == bookId:
            return book;
    return None

## Add a book
@app.post("/books", status_code = status.HTTP_201_CREATED)
def add_a_book(payload: dict = Body()) -> dict :
    book_name = payload.get("name")
    book_desc = payload.get("desc")
    book_genre = payload.get("genre")

    book_id = len(books) + 1
    book = {
        "id": book_id,
        "name" : book_name,
        "desc": book_desc,
        "genre": book_genre
    }

    books.append(book)

    return book

## Update a book
@app.patch("/books/{bookId}", status_code=status.HTTP_200_OK)
def update_a_book(bookId: int, payload: dict = Body()) -> dict:
    book_name = payload.get("name")
    book_desc = payload.get("desc")
    book_genre = payload.get("genre")

    book_to_update = None

    for book in books:
        if book["id"] == bookId:
            book_to_update = book
            break
    
    if book_to_update is None:
        return {
            "message" : f"Book does not exist with id {bookId}"
        }
    
    book_to_update["name"] = book_name
    book_to_update["desc"] = book_desc
    book_to_update["genre"] = book_genre

    return book_to_update

## Delete a book
@app.delete("/books/{bookId}", status_code=status.HTTP_204_NO_CONTENT)
def delete_a_book(bookId: int):
    book_to_delete = None

    for book in books:
        if book["id"] == bookId:
            book_to_delete = book
            break
    
    if book_to_delete is None:
        return JSONResponse(
            content={
                "message" : f"Book does not exist with id {bookId}"
            },
            status_code=status.HTTP_404_NOT_FOUND
        )
    
    books.remove(book_to_delete)

    return Response(status_code=status.HTTP_204_NO_CONTENT)
