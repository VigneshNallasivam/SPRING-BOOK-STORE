package com.example.BookStore.dto;
import com.example.BookStore.model.UserModel;
import lombok.Data;
import lombok.NoArgsConstructor;
import java.util.List;
import java.util.Optional;
@Data
@NoArgsConstructor
public class ResponseUserDTO
{
    private String message;
    private Object object;
    public ResponseUserDTO(String message, UserModel user)
    {
        this.message=message;
        this.object=user;
    }
    public ResponseUserDTO(String message, List<UserModel> user)
    {
        this.message=message;
        this.object=user;
    }
    public ResponseUserDTO(String exception_while_processing_rest_request, String message)
    {
        this.message=exception_while_processing_rest_request;
        this.object=message;
    }
    public ResponseUserDTO(String message, Optional<UserModel> user)
    {
        this.message=message;
        this.object=user;
    }
}
