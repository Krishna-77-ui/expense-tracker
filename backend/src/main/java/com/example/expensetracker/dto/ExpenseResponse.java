package com.example.expensetracker.dto;
import com.example.expensetracker.model.Expense;
import java.math.BigDecimal;
import java.time.LocalDate;
public record ExpenseResponse(Long id, String title, BigDecimal amount, String category, LocalDate expenseDate, String notes) {
 public static ExpenseResponse from(Expense e){return new ExpenseResponse(e.getId(),e.getTitle(),e.getAmount(),e.getCategory(),e.getExpenseDate(),e.getNotes());}
}
