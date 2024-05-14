package com.BankingSpringBoot.BankingApplication.repository;

import com.BankingSpringBoot.BankingApplication.entity.Account;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface AccountRepository extends JpaRepository<Account,Long>
{

}
