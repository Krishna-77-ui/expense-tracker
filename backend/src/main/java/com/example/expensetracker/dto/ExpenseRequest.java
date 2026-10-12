package com.example.expensetracker.dto;
import jakarta.validation.constraints.*;
import java.math.BigDecimal;
import java.time.LocalDate;
public record ExpenseRequest(@NotBlank @Size(max=120) String title, @NotNull @DecimalMin(value="0.01") @Digits(integer=10,fraction=2) BigDecimal amount, @NotBlank @Size(max=60) String category, @NotNull LocalDate expenseDate, @Size(max=500) String notes) {}
