package com.example.expensetracker.controller;
import org.springframework.http.*;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;
import java.util.*;
@RestControllerAdvice
public class ApiExceptionHandler {
 @ExceptionHandler(MethodArgumentNotValidException.class) @ResponseStatus(HttpStatus.BAD_REQUEST)
 public Map<String,Object> validation(MethodArgumentNotValidException ex){List<String> errors=ex.getBindingResult().getFieldErrors().stream().map(e->e.getField()+": "+e.getDefaultMessage()).toList();return Map.of("message","Validation failed","errors",errors);}
 @ExceptionHandler(ResponseStatusException.class) public ResponseEntity<Map<String,String>> status(ResponseStatusException ex){return ResponseEntity.status(ex.getStatusCode()).body(Map.of("message",ex.getReason()==null?"Request failed":ex.getReason()));}
 @ExceptionHandler(Exception.class) @ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR) public Map<String,String> generic(Exception ex){return Map.of("message","Unexpected server error");}
}
