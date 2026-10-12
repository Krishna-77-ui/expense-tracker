package com.example.expensetracker.dto;
import jakarta.validation.constraints.*;
public record LoginRequest(@NotBlank @Email String email, @NotBlank String password) {}
