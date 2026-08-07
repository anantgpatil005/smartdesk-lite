package com.smartdesk.ticket.exception;

import org.springframework.http.*;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.*;

import java.util.*;

@RestControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(MethodArgumentNotValidException.class)

    public ResponseEntity<Map<String,String>>

    validation(MethodArgumentNotValidException ex){

        Map<String,String> errors=new HashMap<>();

        ex.getBindingResult()

                .getFieldErrors()

                .forEach(e->errors.put(

                        e.getField(),

                        e.getDefaultMessage()

                ));

        return ResponseEntity.badRequest().body(errors);

    }

}