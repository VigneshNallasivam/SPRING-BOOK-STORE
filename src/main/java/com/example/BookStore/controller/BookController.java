package com.example.BookStore.controller;
import com.example.BookStore.dto.BookDTO;
import com.example.BookStore.dto.ResponseBookDTO;
import com.example.BookStore.model.BookModel;
import com.example.BookStore.service.IBookService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.List;
import java.util.Optional;
@RestController
@RequestMapping("/homebook")
public class BookController
{
    @Autowired
    IBookService bookService;
    @PostMapping("/insert")
    public ResponseEntity<ResponseBookDTO> insert(@Valid @RequestBody BookDTO bookDTO)
    {
        BookModel book=bookService.insert(bookDTO);
        ResponseBookDTO responseDTO=new ResponseBookDTO("Book details Added!!",book);
        return new ResponseEntity<>(responseDTO, HttpStatus.CREATED);
    }
    @GetMapping("/getAll")
    public ResponseEntity<ResponseBookDTO> getAllBook()
    {
        List<BookModel> bookList=bookService.getAllBook();
        ResponseBookDTO responseDTO=new ResponseBookDTO("All book details have been found!",bookList);
        return new ResponseEntity<>(responseDTO,HttpStatus.FOUND);
    }
    @GetMapping("/getById/{id}")
    public ResponseEntity<ResponseBookDTO> getById(@PathVariable Long id)
    {
        Optional<BookModel> book=bookService.getById(id);
        ResponseBookDTO responseBookDTO=new ResponseBookDTO("Book Found!",book);
        return new ResponseEntity<>(responseBookDTO,HttpStatus.FOUND);
    }
    @DeleteMapping("/delete/{id}")
    public ResponseEntity<ResponseBookDTO> deleteById(@PathVariable Long id)
    {
        bookService.deleteById(id);
        ResponseBookDTO responseBookDTO=new ResponseBookDTO("Book details deleted!","Deleted book id is: "+id);
        return new ResponseEntity<>(responseBookDTO,HttpStatus.GONE);
    }
    @GetMapping("/searchByBookName/{name}")
    public ResponseEntity<ResponseBookDTO> searchByBookName(@PathVariable String name)
    {
        BookModel book=bookService.searchByBookName(name);
        ResponseBookDTO responseBookDTO=new ResponseBookDTO("Book Found....!!",book);
        return new ResponseEntity<>(responseBookDTO,HttpStatus.FOUND);
    }
    @PutMapping("/update/{id}")
    public ResponseEntity<ResponseBookDTO> updateBookById(@Valid @RequestBody BookDTO bookDTO, @PathVariable Long id)
    {
        BookModel book=bookService.updateBookById(bookDTO,id);
        ResponseBookDTO responseBookDTO=new ResponseBookDTO("Book updated!",book);
        return new ResponseEntity<>(responseBookDTO,HttpStatus.ACCEPTED);
    }
    @GetMapping("/sortingAsce")
    public ResponseEntity<ResponseBookDTO> sortingAsce()
    {
        List<BookModel> bookList=bookService.sortingAsce();
        ResponseBookDTO responseBookDTO=new ResponseBookDTO("Book Details In Ascending order!",bookList);
        return new ResponseEntity<>(responseBookDTO,HttpStatus.OK);
    }
    @GetMapping("/sortingDesc")
    public ResponseEntity<ResponseBookDTO> sortingDesc()
    {
        List<BookModel> bookList=bookService.sortingDesc();
        ResponseBookDTO responseBookDTO=new ResponseBookDTO("Book Details In Descending order!",bookList);
        return new ResponseEntity<>(responseBookDTO,HttpStatus.OK);
    }
    @PutMapping("/updateQuantity/{id}")
    public ResponseEntity<ResponseBookDTO> updateQuantity(@Valid @RequestBody BookDTO bookDTO,@PathVariable Long id)
    {
        BookModel book=bookService.updateQuantity(bookDTO,id);
        ResponseBookDTO responseBookDTO=new ResponseBookDTO("Book Details updated!",book);
        return new ResponseEntity<>(responseBookDTO, HttpStatus.ACCEPTED);
    }
}