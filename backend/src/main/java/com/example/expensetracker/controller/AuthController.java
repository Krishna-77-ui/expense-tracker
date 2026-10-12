package com.example.expensetracker.controller;
import com.example.expensetracker.dto.*;
import com.example.expensetracker.service.AuthService;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
@RestController @RequestMapping("/api/auth")
public class AuthController {
 private final AuthService auth; public AuthController(AuthService auth){this.auth=auth;}
 @PostMapping("/register") @ResponseStatus(HttpStatus.CREATED) public AuthResponse register(@Valid @RequestBody RegisterRequest r){return auth.register(r);}
 @PostMapping("/login") public AuthResponse login(@Valid @RequestBody LoginRequest r){return auth.login(r);}
}
