package com.example.BookStore.dto;
import com.example.BookStore.model.BookModel;
import lombok.Data;
import lombok.NoArgsConstructor;
import java.util.List;
import java.util.Optional;
@Data
@NoArgsConstructor
public class ResponseBookDTO
{
    private String message;
    private Object object;
    public ResponseBookDTO(String message, BookModel book)
    {
        this.message=message;
        this.object=book;
    }
    public ResponseBookDTO(String message, List<BookModel> bookList)
    {
        this.message=message;
        this.object=bookList;
    }
    public ResponseBookDTO(String message, Optional<BookModel> book)
    {
        this.message=message;
        this.object=book;
    }
    public ResponseBookDTO(String exception_while_processing_rest_request, String message)
    {
        this.message=exception_while_processing_rest_request;
        this.object=message;
    }
}