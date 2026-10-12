package com.example.expensetracker.model;

import jakarta.persistence.*;
import java.time.LocalDateTime;

@Entity
@Table(name = "users")
public class AppUser {
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY) private Long id;
    @Column(nullable = false, length = 80) private String name;
    @Column(nullable = false, unique = true, length = 160) private String email;
    @Column(nullable = false) private String password;
    @Column(nullable = false) private LocalDateTime createdAt = LocalDateTime.now();
    public AppUser() {}
    public AppUser(String name, String email, String password) { this.name=name; this.email=email; this.password=password; }
    public Long getId(){return id;} public String getName(){return name;} public void setName(String v){name=v;}
    public String getEmail(){return email;} public void setEmail(String v){email=v;} public String getPassword(){return password;} public void setPassword(String v){password=v;}
    public LocalDateTime getCreatedAt(){return createdAt;}
}
