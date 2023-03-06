package com.example.BookStore.exception;
import com.example.BookStore.dto.ResponseBookDTO;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import java.util.List;
import java.util.stream.Collectors;
@ControllerAdvice
public class BookExceptionHandler
{
    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<ResponseBookDTO> handleMethodArgumentNotValidException(MethodArgumentNotValidException ex)
    {
        List<ObjectError> errorList=ex.getBindingResult().getAllErrors();
        List<String> errMsg=errorList.stream()
                .map(objectError -> objectError.getDefaultMessage())
                .collect(Collectors.toList());
        ResponseBookDTO responseBookDTO =new ResponseBookDTO("Exception Occured", errMsg.toString());
        return new ResponseEntity<>(responseBookDTO, HttpStatus.BAD_REQUEST);
    }
    @ExceptionHandler(BookException.class)
    public ResponseEntity<ResponseBookDTO> handleException(BookException ex)
    {
        ResponseBookDTO responseBookDTO =new ResponseBookDTO("Exception Occured",ex.getMessage());
        return new ResponseEntity<>(responseBookDTO, HttpStatus.BAD_GATEWAY);
    }
}