package com.BankingSpringBoot.BankingApplication.controller;

import com.BankingSpringBoot.BankingApplication.dto.AccountDto;
import com.BankingSpringBoot.BankingApplication.service.AccountService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/api/accounts")
public class AccountController
{
    private AccountService accountService;

    public AccountController(AccountService accountService) {
        super();
        this.accountService = accountService;
    }

    @PostMapping("/createAccount")
    public ResponseEntity<AccountDto> addAccount(@RequestBody AccountDto accountDto)
    {
        System.out.println(accountDto);
        return new ResponseEntity<>(accountService.createAccount(accountDto), HttpStatus.CREATED);
    }



    @GetMapping("/get/{id}")
    public ResponseEntity<AccountDto> getAccountById(@PathVariable Long id)
    {
       AccountDto accountDto= accountService.getAccountById(id);
       return new ResponseEntity<>(accountDto,HttpStatus.OK);
    }

    @PutMapping("/{id}/deposit")
    public ResponseEntity<AccountDto> deposit(@PathVariable Long id,@RequestBody Map<String,Double> request)
    {
        AccountDto accountDto=accountService.deposit(id,request.get("amount"));
        return ResponseEntity.ok(accountDto);


    }

    @PutMapping("/{id}/withdraw")
    public ResponseEntity<AccountDto> withdraw(@PathVariable Long id,@RequestBody Map<String,Double> request)
    {
        Double amount = request.get("amount");
        AccountDto accountDto = accountService.withdraw(id,amount);
        return ResponseEntity.ok(accountDto);
    }

    @GetMapping("/getAll")
    public ResponseEntity<List<AccountDto>> getAllAccounts()
    {
        List<AccountDto> accountDto = accountService.getAllAccounts();
        return  ResponseEntity.ok(accountDto);
    }

    @DeleteMapping("/{id}/delete")
    public ResponseEntity<String> deleteAccount(@PathVariable Long id)
    {
        accountService.deleteAccount(id);
        return ResponseEntity.ok("Account Deleted Successfully");
    }
}
