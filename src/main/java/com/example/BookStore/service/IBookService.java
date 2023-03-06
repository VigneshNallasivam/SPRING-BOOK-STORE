package com.example.BookStore.service;
import com.example.BookStore.dto.BookDTO;
import com.example.BookStore.model.BookModel;
import java.util.List;
import java.util.Optional;
public interface IBookService
{
    BookModel insert(BookDTO bookDTO);

    List<BookModel> getAllBook();

    Optional<BookModel> getById(Long id);

    void deleteById(Long id);

    BookModel searchByBookName(String name);

    BookModel updateBookById(BookDTO bookDTO, Long id);

    List<BookModel> sortingAsce();

    List<BookModel> sortingDesc();

    BookModel updateQuantity(BookDTO bookDTO, Long id);
}
