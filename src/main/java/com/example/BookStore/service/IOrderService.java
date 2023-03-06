package com.example.BookStore.service;
import com.example.BookStore.dto.OrderDTO;
import com.example.BookStore.model.OrderModel;
import java.util.List;
public interface IOrderService
{
    OrderModel insert(OrderDTO orderDTO) throws Exception;
    List<OrderModel> getAll();
    OrderModel getById(long id);
    OrderModel updateById(long id, OrderDTO orderDTO) ;
    String deleteById(long orderId, long userId);

}
