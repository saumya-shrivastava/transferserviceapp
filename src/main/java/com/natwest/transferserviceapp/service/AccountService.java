package com.natwest.transferserviceapp.service;

import com.natwest.transferserviceapp.model.Account;
import com.natwest.transferserviceapp.repository.AccountRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
@Service
public class AccountService {

    @Autowired
    AccountRepository accountRepository;
    public Account getAccountDetails(Long accountNumber){
        return accountRepository.getById(accountNumber);
    }

   public Account save(Account account){
        return accountRepository.save(account);
   }
}
