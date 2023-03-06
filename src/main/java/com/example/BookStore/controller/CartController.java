package com.example.BookStore.controller;
import com.example.BookStore.dto.CartDTO;
import com.example.BookStore.dto.ResponseCartDTO;
import com.example.BookStore.model.CartModel;
import com.example.BookStore.service.ICartService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.List;
import java.util.Optional;
@RestController
@RequestMapping("/homecart")
public class CartController
{
    @Autowired
    ICartService cartService;
    @PostMapping("/insert")
    public ResponseEntity<ResponseCartDTO> insert(@Valid @RequestBody CartDTO cartDTO)
    {
        CartModel cart=cartService.insert(cartDTO);
        ResponseCartDTO responseCartDTO=new ResponseCartDTO("cart details added!",cart);
        return new ResponseEntity<>(responseCartDTO, HttpStatus.CREATED);
    }
    @GetMapping("/getAll")
    public ResponseEntity<ResponseCartDTO> getAll()
    {
        List<CartModel> cartList=cartService.getAll();
        ResponseCartDTO responseCartDTO=new ResponseCartDTO("All cart details found!",cartList);
        return new ResponseEntity<>(responseCartDTO,HttpStatus.FOUND);
    }
    @GetMapping("/getById/{id}")
    public ResponseEntity<ResponseCartDTO> getById(@PathVariable Long id)
    {
        Optional<CartModel> cart=cartService.getById(id);
        ResponseCartDTO responseCartDTO=new ResponseCartDTO("Searched details by id found!",cart);
        return new ResponseEntity<>(responseCartDTO,HttpStatus.FOUND);
    }
    @DeleteMapping("/delete/{id}")
    public ResponseEntity<ResponseCartDTO> deleteById(@PathVariable Long id)
    {
        cartService.deleteById(id);
        ResponseCartDTO responseCartDTO=new ResponseCartDTO("deleted!","Deleted cart id is: "+id);
        return new ResponseEntity<>(responseCartDTO,HttpStatus.GONE);
    }
    @PutMapping("/updateById/{id}")
    public ResponseEntity<ResponseCartDTO> updateById(@Valid @RequestBody CartDTO cartDTO,@PathVariable Long id)
    {
        CartModel cart=cartService.updateById(cartDTO,id);
        ResponseCartDTO responseCartDTO=new ResponseCartDTO("updated!",cart);
        return new ResponseEntity<>(responseCartDTO,HttpStatus.ACCEPTED);
    }
    @PutMapping("/updateQuantity/{id}")
    public ResponseEntity<ResponseCartDTO> updateQuantity(@Valid @RequestBody CartDTO cartDTO,@PathVariable Long id)
    {
        CartModel cart=cartService.UpdateQuantity(cartDTO,id);
        ResponseCartDTO responseCartDTO=new ResponseCartDTO("quantity updated!",cart);
        return new ResponseEntity<>(responseCartDTO,HttpStatus.ACCEPTED);
    }
}
