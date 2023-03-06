package com.example.BookStore.dto;

import com.example.BookStore.model.CartModel;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;
import java.util.Optional;
@Data
@NoArgsConstructor
public class ResponseCartDTO
{
    private String message;
    private Object object;
    public ResponseCartDTO(String exception_while_processing_rest_request, String message)
    {
        this.message=exception_while_processing_rest_request;
        this.object=message;
    }
    public ResponseCartDTO(String message, CartModel cart)
    {
        this.message=message;
        this.object=cart;
    }
    public ResponseCartDTO(String message, List<CartModel> cartList)
    {
        this.message=message;
        this.object=cartList;
    }
    public ResponseCartDTO(String message, Optional<CartModel> cart)
    {
        this.message=message;
        this.object=cart;
    }
}
