package com.example.BookStore.service;
import com.example.BookStore.dto.CartDTO;
import com.example.BookStore.exception.CartException;
import com.example.BookStore.model.BookModel;
import com.example.BookStore.model.CartModel;
import com.example.BookStore.model.UserModel;
import com.example.BookStore.repository.BookRepository;
import com.example.BookStore.repository.CartRepository;
import com.example.BookStore.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;
import java.util.Optional;
@Service
public class CartService implements  ICartService
{
    @Autowired
    CartRepository cartRepo;
    @Autowired
    UserRepository userRepo;
    @Autowired
    BookRepository bookRepo;
    @Override
    public CartModel insert(CartDTO cartDTO)
    {
        Optional<UserModel> user=userRepo.findById(cartDTO.getUserId());
        Optional<BookModel> book=bookRepo.findById(cartDTO.getBookId());
        if(user.isPresent() && book.isPresent())
        {
            CartModel cart=new CartModel(user.get(),book.get(),cartDTO.getQuantity());
            cartRepo.save(cart);
            return cart;
        }
        else
        {
            throw new CartException("Give correct details!");
        }
    }
    @Override
    public List<CartModel> getAll()
    {
        List<CartModel> cartList=cartRepo.findAll();
        return cartList;
    }
    @Override
    public Optional<CartModel> getById(Long id)
    {
        Optional<CartModel> cart=cartRepo.findById(id);
        if (cart.isPresent())
        {
            return cart;
        }
        else
        {
            throw new CartException("Cannot find the cart id: "+id);
        }
    }
    @Override
    public void deleteById(Long id)
    {
        Optional<CartModel> cart=cartRepo.findById(id);
        if (cart.isPresent())
        {
            cartRepo.deleteById(id);
        }
        else
        {
            throw new CartException("Cannot find cart id: "+id);
        }
    }
    @Override
    public CartModel updateById(CartDTO cartDTO,Long id)
    {
        Optional<UserModel> user=userRepo.findById(cartDTO.getUserId());
        Optional<BookModel> book=bookRepo.findById(cartDTO.getBookId());
        CartModel cart=cartRepo.findById(id).get();
        if(cartRepo.findById(id).isPresent() && book.isPresent() && user.isPresent())
        {
            cart.setUserId(user.get());
            cart.setBookId(book.get());
            cart.setQuantity(cartDTO.getQuantity());
            cartRepo.save(cart);
            return cart;
        }
        else
        {
            throw new CartException("Incorrect Details! check Again!");
        }
    }
    @Override
    public CartModel UpdateQuantity(CartDTO cartDTO, Long id)
    {
        CartModel cart=cartRepo.findById(id).get();
        if(cartRepo.findById(id).isPresent())
        {
            cart.setQuantity(cartDTO.getQuantity());
            cartRepo.save(cart);
            return cart;
        }
        else
        {
            throw new CartException("Cannot find cart id: "+id);
        }
    }
}
