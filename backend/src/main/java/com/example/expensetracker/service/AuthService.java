package com.example.expensetracker.service;
import com.example.expensetracker.dto.*;
import com.example.expensetracker.model.AppUser;
import com.example.expensetracker.repository.UserRepository;
import com.example.expensetracker.security.JwtService;
import org.springframework.http.HttpStatus;
import org.springframework.security.authentication.*;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;
import org.springframework.security.core.AuthenticationException;
@Service
public class AuthService {
 private final UserRepository users; private final PasswordEncoder encoder; private final AuthenticationManager authManager; private final JwtService jwt;
 public AuthService(UserRepository users,PasswordEncoder encoder,AuthenticationManager authManager,JwtService jwt){this.users=users;this.encoder=encoder;this.authManager=authManager;this.jwt=jwt;}
 public AuthResponse register(RegisterRequest r){if(users.existsByEmailIgnoreCase(r.email()))throw new ResponseStatusException(HttpStatus.CONFLICT,"An account with this email already exists");AppUser u=users.save(new AppUser(r.name().trim(),r.email().trim().toLowerCase(),encoder.encode(r.password())));return response(u);}
 public AuthResponse login(LoginRequest r){try{authManager.authenticate(new UsernamePasswordAuthenticationToken(r.email().trim().toLowerCase(),r.password()));}catch(AuthenticationException e){throw new ResponseStatusException(HttpStatus.UNAUTHORIZED,"Invalid email or password");}AppUser u=users.findByEmailIgnoreCase(r.email()).orElseThrow(()->new ResponseStatusException(HttpStatus.UNAUTHORIZED,"Invalid email or password"));return response(u);}
 private AuthResponse response(AppUser u){return new AuthResponse(jwt.generate(u.getEmail()),"Bearer",u.getId(),u.getName(),u.getEmail());}
}
