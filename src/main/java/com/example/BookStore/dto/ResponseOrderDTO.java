package com.example.BookStore.dto;
import com.example.BookStore.model.OrderModel;
import lombok.Data;
import lombok.NoArgsConstructor;
import java.util.List;
@Data
@NoArgsConstructor
public class ResponseOrderDTO
{
    private String message;
    private Object object;
    public ResponseOrderDTO(String message, OrderModel order)
    {
        this.message=message;
        this.object=order;
    }
    public ResponseOrderDTO(String message, List<OrderModel> orderList)
    {
        this.message=message;
        this.object=orderList;
    }
    public ResponseOrderDTO(String exception_while_processing_rest_request, String message)
    {
        this.message=exception_while_processing_rest_request;
        this.message=message;
    }
}
