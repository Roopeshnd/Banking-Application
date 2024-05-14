package com.BankingSpringBoot.BankingApplication.service.impl;

import com.BankingSpringBoot.BankingApplication.dto.AccountDto;
import com.BankingSpringBoot.BankingApplication.entity.Account;
import com.BankingSpringBoot.BankingApplication.mapper.AccountMapper;
import com.BankingSpringBoot.BankingApplication.repository.AccountRepository;
import com.BankingSpringBoot.BankingApplication.service.AccountService;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Service
public class AccountServiceImpl implements AccountService
{

    private AccountRepository accountRepository;

    public AccountServiceImpl(AccountRepository accountRepository) {
        super();
        this.accountRepository = accountRepository;
    }

    @Override
    public AccountDto createAccount(AccountDto accountDto) {
        Account account = AccountMapper.maptoAccount(accountDto);
       Account savedAccount= accountRepository.save(account);
        return AccountMapper.mapToAccountDto(savedAccount);
    }

    @Override
    public AccountDto getAccountById(Long id) {
        Account account=accountRepository.findById(id).orElseThrow(( )-> new RuntimeException("Account does not exits "));
        return AccountMapper.mapToAccountDto(account);
    }

    @Override
    public AccountDto deposit(Long id, double amount) {
        Account account=accountRepository.findById(id).orElseThrow(( )-> new RuntimeException("Account does not exits "));
        double totalBalance = account.getBalance()+amount;
        account.setBalance(totalBalance);
        Account savedAccount = accountRepository.save(account);
        return AccountMapper.mapToAccountDto(savedAccount);
    }

    @Override
    public AccountDto withdraw(Long id, double amount) {
        Account account=accountRepository.findById(id).orElseThrow(( )-> new RuntimeException("Account does not exits "));
        if(account.getBalance()<amount)
        {
            throw  new RuntimeException("Insufficient balance ");
        }
        double totalBalance = account.getBalance()-amount;
        account.setBalance(totalBalance);
        Account savedAccount=accountRepository.save(account);

        return AccountMapper.mapToAccountDto(savedAccount);
    }

    @Override
    public List<AccountDto> getAllAccounts() {
        return accountRepository.findAll().stream().map((account) -> AccountMapper.mapToAccountDto(account)).collect(Collectors.toList());

    }

    @Override
    public void deleteAccount(Long id) {
        Account account=accountRepository.findById(id).orElseThrow(( )-> new RuntimeException("Account does not exits "));
        accountRepository.delete(account);
    }


}
