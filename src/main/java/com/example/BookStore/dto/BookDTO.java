package com.example.BookStore.dto;
import jakarta.validation.constraints.Pattern;
import lombok.Data;
import lombok.NoArgsConstructor;
@Data
@NoArgsConstructor
public class BookDTO
{
    @Pattern(regexp = "^[A-Z]{1}[a-z]{2,}\\s{0,}[A-Z]{1}[a-z]{2,}$",message = "Book name start with capital letter!")
    private String bookName;
    @Pattern(regexp = "^[A-Z]{1}[a-z]{2,}\\s{0,}[A-Z]{1}[a-z]{2,}$",message = "Author name start with capital letter!")
    private String authorName;
    private String bookDescription;
    private String bookImg;
    private long price;
    private long quantity;
}