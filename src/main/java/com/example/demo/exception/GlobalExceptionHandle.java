package com.example.demo.exception;

import com.example.demo.model.respone.FileResponse;
import com.example.demo.utils.Response;
import org.springframework.context.support.DefaultMessageSourceResolvable;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.multipart.MaxUploadSizeExceededException;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

//@RestControllerAdvice
//public class GlobalExceptionHandle extends ResponseEntityExceptionHandler {
//    @Override
//    protected ResponseEntity<Object> handleMethodArgumentNotValid(MethodArgumentNotValidException ex, HttpHeaders headers, HttpStatusCode status, WebRequest request) {
//        List<String> error=ex.getBindingResult().getFieldErrors()
//                .stream().map(DefaultMessageSourceResolvable::getDefaultMessage).toList();
//
//        return new ResponseEntity<>(Response.<Object>badRequest().setMessage(error).setSuccess(false), HttpStatus.BAD_REQUEST);
//    }
//}
@RestControllerAdvice
public class GlobalExceptionHandle {
    @ExceptionHandler(MethodArgumentNotValidException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    protected ResponseEntity<Object>  handleMethodArgumentNotValid(MethodArgumentNotValidException ex) {
        Map<String,String> errorMap= new HashMap<>();
        ex.getBindingResult().getFieldErrors().forEach(error->{
            errorMap.put(error.getField(),error.getDefaultMessage());
        });
        return new ResponseEntity<>(Response.<Object>badRequest().setMessage(errorMap).setSuccess(false),HttpStatus.BAD_REQUEST);
    }


    @ExceptionHandler(MaxUploadSizeExceededException.class)
    protected ResponseEntity<?> handleMaxFileExceed(MaxUploadSizeExceededException ex) {

        return new ResponseEntity<>(Response.<ResponseEntity<Object>>badRequest().setMessage(ex.getMessage()), HttpStatus.BAD_REQUEST);
    }

    ///IllegalArgumentException

    @ExceptionHandler(IllegalArgumentException.class)
    protected ResponseEntity<Object> handleIllegalArgumentException(IllegalArgumentException ex) {

        return new ResponseEntity<>(Response.<ResponseEntity<Object>>badRequest().setMessage(ex.getMessage()), HttpStatus.BAD_REQUEST);
    }
}


