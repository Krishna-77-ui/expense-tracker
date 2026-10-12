package com.example.expensetracker.controller;
import com.example.expensetracker.dto.*;
import com.example.expensetracker.service.ExpenseService;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;
import java.util.List;
@RestController @RequestMapping("/api/expenses")
public class ExpenseController {
 private final ExpenseService service; public ExpenseController(ExpenseService service){this.service=service;}
 @GetMapping public List<ExpenseResponse> list(Authentication a){return service.list(a.getName());}
 @GetMapping("/summary") public ExpenseSummary summary(Authentication a){return service.summary(a.getName());}
 @GetMapping("/{id}") public ExpenseResponse get(Authentication a,@PathVariable Long id){return service.get(a.getName(),id);}
 @PostMapping @ResponseStatus(HttpStatus.CREATED) public ExpenseResponse create(Authentication a,@Valid @RequestBody ExpenseRequest r){return service.create(a.getName(),r);}
 @PutMapping("/{id}") public ExpenseResponse update(Authentication a,@PathVariable Long id,@Valid @RequestBody ExpenseRequest r){return service.update(a.getName(),id,r);}
 @DeleteMapping("/{id}") @ResponseStatus(HttpStatus.NO_CONTENT) public void delete(Authentication a,@PathVariable Long id){service.delete(a.getName(),id);}
}
