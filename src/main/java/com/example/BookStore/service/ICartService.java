package com.example.BookStore.service;
import com.example.BookStore.dto.CartDTO;
import com.example.BookStore.model.CartModel;
import java.util.List;
import java.util.Optional;
public interface ICartService
{
    List<CartModel> getAll();

    Optional<CartModel> getById(Long id);
    void deleteById(Long id);

    CartModel updateById(CartDTO cartDTO,Long id);

    CartModel UpdateQuantity(CartDTO cartDTO, Long id);

    CartModel insert(CartDTO cartDTO);
}