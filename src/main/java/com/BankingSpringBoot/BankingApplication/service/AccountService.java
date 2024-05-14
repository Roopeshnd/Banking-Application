package com.BankingSpringBoot.BankingApplication.service;

import com.BankingSpringBoot.BankingApplication.dto.AccountDto;
import com.BankingSpringBoot.BankingApplication.entity.Account;
import com.BankingSpringBoot.BankingApplication.repository.AccountRepository;
import org.springframework.stereotype.Service;

import java.util.List;


public interface AccountService
{



    AccountDto createAccount(AccountDto accountDto);
    AccountDto getAccountById(Long id);

    AccountDto deposit(Long id,double amount);

    AccountDto withdraw(Long id ,double amount);

    List<AccountDto> getAllAccounts();

    void deleteAccount(Long id);

}
