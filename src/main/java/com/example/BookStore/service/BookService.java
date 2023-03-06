package com.example.BookStore.service;
import com.example.BookStore.dto.BookDTO;
import com.example.BookStore.exception.BookException;
import com.example.BookStore.model.BookModel;
import com.example.BookStore.repository.BookRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;
import java.util.Optional;
@Service
public class BookService implements IBookService
{
    @Autowired
    BookRepository bookRepo;
    @Override
    public BookModel insert(BookDTO bookDTO)
    {
        BookModel book=new BookModel(bookDTO);
        bookRepo.save(book);
        return book;
    }
    @Override
    public List<BookModel> getAllBook()
    {
        List<BookModel> bookList=bookRepo.findAll();
        return bookList;
    }
    @Override
    public Optional<BookModel> getById(Long id){
        Optional<BookModel> book=bookRepo.findById(id);
        if(book.isPresent()){
            return book;
        }else {
            throw new BookException("Cannot Find book id: "+id);
        }
    }
    @Override
    public void deleteById(Long id)
    {
        Optional<BookModel> book=bookRepo.findById(id);
        if(book.isPresent())
        {
            bookRepo.deleteById(id);
        }
        else {
            throw new BookException("Cannot find book id: "+id);
        }
    }
    @Override
    public BookModel searchByBookName(String name)
    {
        BookModel book=bookRepo.findByName(name);
        if(bookRepo.findByName(name)==null)
        {
            throw new BookException("Cannot find book name: "+name);
        }
        else
        {
            return book;
        }
    }
    @Override
    public BookModel updateBookById(BookDTO bookDTO,Long id)
    {
        BookModel book=bookRepo.findById(id).get();
        if(bookRepo.findById(id).isPresent())
        {
            book.setBookName(bookDTO.getBookName());
            book.setAuthorName(bookDTO.getAuthorName());
            book.setBookDescription(book.getBookDescription());
            book.setBookImg(bookDTO.getBookImg());
            book.setPrice(bookDTO.getPrice());
            book.setQuantity(bookDTO.getQuantity());
            bookRepo.save(book);
            return book;
        }
        else
        {
            throw new BookException("Cannot Find id: "+id);
        }
    }
    @Override
    public List<BookModel> sortingAsce()
    {
        List<BookModel> bookList=bookRepo.sortingAsce();
        return bookList;
    }
    @Override
    public List<BookModel> sortingDesc()
    {
        List<BookModel> bookList=bookRepo.sortingDesc();
        return bookList;
    }
    @Override
    public BookModel updateQuantity(BookDTO bookDTO, Long id)
    {
        Optional<BookModel> bookList=bookRepo.findById(id);
        if(bookList.isPresent())
        {
            BookModel book=bookRepo.findById(id).get();
            book.setQuantity(bookDTO.getQuantity());
            bookRepo.save(book);
            return book;
        }
        else
        {
            throw new BookException("Cannot Find id: "+id);
        }
    }
}
