package com.example.expensetracker.service;
import com.example.expensetracker.dto.*;
import com.example.expensetracker.model.*;
import com.example.expensetracker.repository.*;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.server.ResponseStatusException;
import java.math.BigDecimal;
import java.util.*;
import java.util.stream.Collectors;
@Service
public class ExpenseService {
 private final ExpenseRepository expenses; private final UserRepository users;
 public ExpenseService(ExpenseRepository expenses,UserRepository users){this.expenses=expenses;this.users=users;}
 private AppUser user(String email){return users.findByEmailIgnoreCase(email).orElseThrow(()->new ResponseStatusException(HttpStatus.UNAUTHORIZED,"User not found"));}
 public List<ExpenseResponse> list(String email){return expenses.findAllByUserIdOrderByExpenseDateDesc(user(email).getId()).stream().map(ExpenseResponse::from).toList();}
 public ExpenseResponse get(String email,Long id){return ExpenseResponse.from(owned(email,id));}
 @Transactional public ExpenseResponse create(String email,ExpenseRequest r){Expense e=new Expense();apply(e,r);e.setUser(user(email));return ExpenseResponse.from(expenses.save(e));}
 @Transactional public ExpenseResponse update(String email,Long id,ExpenseRequest r){Expense e=owned(email,id);apply(e,r);return ExpenseResponse.from(expenses.save(e));}
 @Transactional public void delete(String email,Long id){expenses.delete(owned(email,id));}
 public ExpenseSummary summary(String email){var list=expenses.findAllByUserIdOrderByExpenseDateDesc(user(email).getId());BigDecimal total=list.stream().map(Expense::getAmount).reduce(BigDecimal.ZERO,BigDecimal::add);Map<String,BigDecimal> categories=list.stream().collect(Collectors.groupingBy(Expense::getCategory,TreeMap::new,Collectors.reducing(BigDecimal.ZERO,Expense::getAmount,BigDecimal::add)));return new ExpenseSummary(total,list.size(),categories);}
 private Expense owned(String email,Long id){return expenses.findByIdAndUserId(id,user(email).getId()).orElseThrow(()->new ResponseStatusException(HttpStatus.NOT_FOUND,"Expense not found"));}
 private void apply(Expense e,ExpenseRequest r){e.setTitle(r.title().trim());e.setAmount(r.amount());e.setCategory(r.category().trim());e.setExpenseDate(r.expenseDate());e.setNotes(r.notes()==null?null:r.notes().trim());}
}
