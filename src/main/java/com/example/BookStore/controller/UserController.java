package com.example.BookStore.controller;
import com.example.BookStore.dto.LoginDTO;
import com.example.BookStore.dto.ResponseUserDTO;
import com.example.BookStore.dto.UserDTO;
import com.example.BookStore.model.UserModel;
import com.example.BookStore.service.IUserService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.List;
import java.util.Optional;
@RestController
@RequestMapping("/homeuser")
public class UserController
{
    @Autowired
    IUserService userService;
    @PostMapping("/register")
    public ResponseEntity<ResponseUserDTO> register(@Valid @RequestBody UserDTO userDTO) throws Exception
    {
        UserModel user=userService.register(userDTO);
        ResponseUserDTO responseUserDTO =new ResponseUserDTO("User Registered",user);
        return new ResponseEntity<>(responseUserDTO, HttpStatus.CREATED);
    }
    @GetMapping("/getAll")
    public ResponseEntity<ResponseUserDTO> getAll()
    {
        List<UserModel> user=userService.getAll();
        ResponseUserDTO responseUserDTO =new ResponseUserDTO("All user details have been found!",user);
        return new ResponseEntity<>(responseUserDTO,HttpStatus.FOUND);
    }
    @GetMapping("/getById/{id}")
    public ResponseEntity<ResponseUserDTO> getById(@PathVariable Long id){
        Optional<UserModel> user=userService.getById(id);
        ResponseUserDTO responseUserDTO =new ResponseUserDTO("User-ID Found!",user);
        return new ResponseEntity<>(responseUserDTO,HttpStatus.FOUND);
    }
    @GetMapping("/getByEmailID/{email}")
    public ResponseEntity<ResponseUserDTO> getByEmailID(@PathVariable String email)
    {
        UserModel users=userService.getByEmailID(email);
        ResponseUserDTO responseUserDTO =new ResponseUserDTO("User-Email Found!",users);
        return new ResponseEntity<>(responseUserDTO,HttpStatus.FOUND);
    }
    @PutMapping("/forgotPassword/{email}")
    public ResponseEntity<ResponseUserDTO> forgotPassword(@Valid @RequestBody UserDTO userDTO, @PathVariable String email)
    {
        UserModel user=userService.forgotPassword(userDTO,email);
        ResponseUserDTO responseUserDTO =new ResponseUserDTO("Password Changed successfully!",user);
        return new ResponseEntity<>(responseUserDTO,HttpStatus.ACCEPTED);
    }

    @PutMapping("/changePassword2/{id}/{token}")
    public ResponseEntity<ResponseUserDTO> changePassword2(@Valid @RequestBody UserDTO userDTO,@PathVariable long id,@PathVariable String token)
    {
        UserModel userModel = userService.changePassword2(userDTO,token,id);
        ResponseUserDTO responseUserDTO = new ResponseUserDTO("Password Changed Succesfully",userModel);
        return new ResponseEntity<>(responseUserDTO,HttpStatus.OK);
    }
    @PutMapping("/updateUserByEmail/{email}")
    public ResponseEntity<ResponseUserDTO> updateUserByEmail(@Valid @RequestBody UserDTO userDTO, @PathVariable String email)
    {
        UserModel user=userService.updateUserByEmail(userDTO,email);
        ResponseUserDTO responseUserDTO =new ResponseUserDTO("Updated successfully!",user);
        return new ResponseEntity<>(responseUserDTO, HttpStatus.OK);
    }
    @PostMapping("/login")
    public ResponseEntity<ResponseUserDTO> login(@Valid @RequestBody LoginDTO loginDTO)
    {
        String result=userService.login(loginDTO);
        ResponseUserDTO responseUserDTO=new ResponseUserDTO("Welcome!..User You Logged-In Successfully",result);
        return  new ResponseEntity<>(responseUserDTO, HttpStatus.OK);
    }
    @DeleteMapping("/deleteById/{id}")
    public ResponseEntity<ResponseUserDTO> deleteById(@PathVariable long id)
    {
        String value = userService.deleteById(id);
        ResponseUserDTO responseUserDTO = new ResponseUserDTO("User Id Deleted",value);
        return new ResponseEntity<>(responseUserDTO,HttpStatus.OK);
    }
}
