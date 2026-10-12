package com.example.expensetracker.model;

import jakarta.persistence.*;
import java.math.BigDecimal;
import java.time.LocalDate;

@Entity
@Table(name = "expenses")
public class Expense {
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY) private Long id;
    @Column(nullable=false, length=120) private String title;
    @Column(nullable=false, precision=12, scale=2) private BigDecimal amount;
    @Column(nullable=false, length=60) private String category;
    @Column(nullable=false) private LocalDate expenseDate;
    @Column(length=500) private String notes;
    @ManyToOne(fetch=FetchType.LAZY, optional=false) @JoinColumn(name="user_id", nullable=false) private AppUser user;
    public Expense() {}
    public Long getId(){return id;} public String getTitle(){return title;} public void setTitle(String v){title=v;}
    public BigDecimal getAmount(){return amount;} public void setAmount(BigDecimal v){amount=v;} public String getCategory(){return category;} public void setCategory(String v){category=v;}
    public LocalDate getExpenseDate(){return expenseDate;} public void setExpenseDate(LocalDate v){expenseDate=v;} public String getNotes(){return notes;} public void setNotes(String v){notes=v;}
    public AppUser getUser(){return user;} public void setUser(AppUser v){user=v;}
}
